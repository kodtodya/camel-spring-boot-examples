package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerToSedaRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("timer://foo?period=1000").id("timerEndpoint").routeId("Timer-Seda-Route")
				.setBody().simple("hello buddy,  we have a problem.. and that needs to be saved in file via activemq.. :D").id("route-setBody")
				.log("Timer endpoint triggered..").id("producer-log")
		.to("seda:testSedaEndpoint?multipleConsumers=true&waitForTaskToComplete=Never&blockWhenFull=true7discardIfNoConsumers=true").id("sedaProducer");
	}

}
