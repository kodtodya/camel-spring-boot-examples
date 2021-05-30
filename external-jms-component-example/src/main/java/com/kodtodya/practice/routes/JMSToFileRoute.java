package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JMSToFileRoute extends RouteBuilder{

	@Value("${spring.jms.queue}")
	public String queueName;

	@Override
	public void configure() throws Exception {
		from("jms:queue:" + queueName).id("jms-consumer").routeId("jms-to-file-route")
				.log("JMS consumer triggered..").id("consumer-log")
				.to("file:{{output.location}}").id("file-producer");
	}

}
