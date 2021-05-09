package com.kodtodya.practice.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestExecutionRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		onException(CamelExecutionException.class)
				.to("log:exceptionLogger");
		
		// call the embedded rest service from the PetController
		restConfiguration().component("servlet").port(8080).bindingMode(RestBindingMode.auto);


		rest()
				// post service for inserting data
				.post("/sessions/{course}")
				.produces("application/json")
				.to("direct:insertEndpoint")

				// get service for fetching data from database
				.get("/getsessions")
				.produces("application/json")
				.to("direct:fetchEndpoint")
			;
	}
}