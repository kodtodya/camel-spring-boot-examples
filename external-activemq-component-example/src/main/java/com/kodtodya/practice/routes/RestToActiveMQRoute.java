package com.kodtodya.practice.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestToActiveMQRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		onException(CamelExecutionException.class)
				.handled(true)
				.redeliveryDelay(2000)
				.maximumRedeliveries(5)
				// asynchronous redelivery
				.asyncDelayedRedelivery()
				// log the exception details
				.to("log:exceptionLogger");

		// call the embedded rest service from the RestController
		restConfiguration()
				.component("servlet")
				.port(8080)
				.bindingMode(RestBindingMode.auto);

		// actual rest service implementtion
		rest().post("/sendmsg")
				.produces("application/json")
				.to("direct:message-consumer");

		// consume the call received in rest for processing
		from("direct:message-consumer")
				// logging step
				.log("Message received and will be sent to Activemq: ${body}").id("activemq-producer-log")
				// send to activemq over myQ queue
				.to("activemq:queue:myQ").id("amq-producer")

				// set header for rest service response
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
				.setBody(constant("message sent to activemq"));
	}

}
