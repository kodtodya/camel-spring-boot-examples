package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerToDirectVmRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		from("timer:myTimer?period=10000&repeatCount=2").id("timer-component").routeId("timer-to-direct-vm-route")
				.setBody().simple("Current time is: ${date:now:yyyy-MM-dd HH:mm:ss}")
				// sending sync call to direct-vm component
				.to("direct-vm:foo").id("direct-vm-producer-component")
				// log statement
				.log("Final body: ${body}").id("timer-logger");

		// consumes the direct-vm component calls
		from("direct-vm:foo").id("direct-vm-consumer-component").routeId("direct-vm-to-log-route")
				// log statement
				.log("body inside second route before setting up new body: ${body}").id("direct-vm-logger")
				// modify current body content
				.setBody().simple("${body.replace('Current time is','Last execution time was')}");
	}

}