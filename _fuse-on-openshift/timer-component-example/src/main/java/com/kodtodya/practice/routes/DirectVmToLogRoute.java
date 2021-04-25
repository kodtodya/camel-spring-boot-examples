package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DirectVmToLogRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		from("direct-vm:foo").id("direct-vm-consumer")
				.routeId("direct-vm-to-log-route")
				.to("log:my-logger").id("log-producer");
	}

}