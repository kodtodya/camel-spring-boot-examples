package com.kodtodya.practice.routes;
import com.kodtodya.practice.beans.Customer;
import com.kodtodya.practice.services.TransformationService;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestExecutionRoute extends RouteBuilder {

	@Autowired
	private TransformationService service;

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
				//.port(8080)
				.bindingMode(RestBindingMode.auto)
		;

		// rest-post service implementation accepting the json
		rest()
				.consumes("application/json")
				//.produces("application/json")
				.post("/customer")
				.type(Customer.class)
				.route()
				.log("Incoming Body is ${body}")
				.setProperty("out-file-name", simple("${body.getName}"))
				.bean(service,"transformMessage")
				.log("Outgoing pojo Body is ${body}")
				.to("file:{{output.location}}?fileName=${exchangeProperty.out-file-name}")
				.end();
	}


}