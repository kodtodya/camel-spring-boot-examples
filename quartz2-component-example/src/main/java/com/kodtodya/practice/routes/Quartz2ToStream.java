package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Quartz2ToStream extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		/*from("quartz2://myTimerGroup/myTimer?cron=0/5+*+*+?+*+*").id("quartzConsumer")
				.setBody().simple("the current time is ${header.fireTime}")
				.to("stream:out").id("streamOutProducer");*/
	}

}
