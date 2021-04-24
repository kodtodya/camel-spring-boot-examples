package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Quartz2ToLog extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("quartz2://myQuartzGroup/myScheduler?cron=0/5+*+*+?+*+*").id("quartzConsumer")
				.setBody().simple("the current time is ${header.fireTime}")
				.to("log:my-sample-logger").id("log-endpoint");
	}

}
