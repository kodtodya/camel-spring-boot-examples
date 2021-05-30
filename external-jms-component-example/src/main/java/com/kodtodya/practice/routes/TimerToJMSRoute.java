package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TimerToJMSRoute extends RouteBuilder{

	@Value("${spring.jms.queue}")
	public String queueName;

	@Override
	public void configure() throws Exception {
		from("timer://foo?period=1000").id("timer-endpoint").routeId("timer-jms-route")
				.setBody().simple("hello buddy,  we have a problem.. and that needs to be saved in file via JMS.. :D").id("route-setBody")
				.log("Timer endpoint triggered..").id("producer-log")
		.to("jms:queue:" + queueName).id("jms-producer");
	}

}
