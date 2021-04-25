package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerToDirectVmRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		from("timer:myTimer?period=20000&repeatCount=2").id("timer-component")
				.routeId("timer-to-direct-vm-route")
				.setBody().simple("Current time is: ${date:now:yyyy-MM-dd HH:mm:ss}")
			.to("direct-vm:foo").id("direc-vm-producer");
	}

}