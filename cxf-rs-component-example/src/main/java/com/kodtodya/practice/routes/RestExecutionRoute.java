package com.kodtodya.practice.routes;

import com.kodtodya.practice.processor.NumberProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpOperationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestExecutionRoute extends RouteBuilder {

	@Autowired
	private NumberProcessor processor;

	@Override
	public void configure() throws Exception {

		onException(HttpOperationFailedException.class)
				.handled(true)
				.redeliveryDelay(2000)
				.maximumRedeliveries(5)
				// asynchronous redelivery
				.asyncDelayedRedelivery()
				// log the exeption details
				.to("log:exceptionLogger");

		// consume the call received in rest for processing
		from("cxfrs:{{cxfrs-service.url}}?{{cxfrs-service.query-params}}")
				.log("We received number to check number is prime : ${body}")
				.process(processor)
				.log("Response sent to client: ${body}");
	}
}