package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerToActiveMQRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// generate event
		from("timer://foo?period=1000").id("timerEndpoint").routeId("timer-activemq-route")
				// set some body
				.setBody().simple("hello buddy,  we have a problem.. and that needs to be saved in file via activemq.. :D").id("route-setBody")
				// logging step
				.log("Timer endpoint triggered..").id("producer-log")
				// send to activemq over myQ queue
				.to("activemq:queue:myQ").id("amq-producer");
	}

}
