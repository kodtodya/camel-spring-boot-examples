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
				.to("log:exceptionLogger");
		// call the embedded rest service from the PetController
		restConfiguration().component("servlet").port(8080).bindingMode(RestBindingMode.auto);

		rest().get("/checkprime/{number}").produces("application/json")
				.to("direct:prime");

		from("direct:prime")
				.setBody().simple("${header.number}")
				.bean(service);
	}
}