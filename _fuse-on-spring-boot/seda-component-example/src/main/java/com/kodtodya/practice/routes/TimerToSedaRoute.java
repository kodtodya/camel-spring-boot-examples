package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerToSedaRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("timer://foo?period=1000").id("timer-endpoint").routeId("timer-seda-route")
				// set body as timer does not set body
				.setBody().constant("hello buddy,  we are testing async functionality in camel..and this needs to be tested using seda.. :D").id("route-setBody")
				// log statement
				.log("Timer endpoint triggered..").id("producer-log")
				// producer body to seda component
				.to("seda:testSedaEndpoint?multipleConsumers=true&waitForTaskToComplete=Never&blockWhenFull=true7discardIfNoConsumers=true").id("seda-producer");
	}

}
