package com.kodtodya.practice.routes;
import com.kodtodya.practice.services.PrimeNumberService;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestExecutionRoute extends RouteBuilder {

	@Autowired
	private PrimeNumberService service;

	@Override
	public void configure() throws Exception {

		onException(CamelExecutionException.class)
				.handled(true)
				.redeliveryDelay(2000)
				.maximumRedeliveries(5)
				// asynchronous redelivery
				.asyncDelayedRedelivery()
				// log the exeption details
				.to("log:exceptionLogger");

		// call the embedded rest service from the RestController
		restConfiguration()
				.component("jetty")
				.port(8888)
				.bindingMode(RestBindingMode.auto);

		// actual rest service implementtion
		rest().get("/checkprime/{number}")
				.produces("application/json")
				.to("direct:prime");

		// consume the call received in rest for processing
		from("direct:prime")
				.log("inside the camel-route, we have received ${header.number} to check if it is prime or not")
				.setBody().simple("${header.number}")
				.bean(service);
	}
}