package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JMSToFileRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("jms:queue:myQ").id("jmsConsumer").routeId("JMS-to-File-Route")
				.log("JMS consumer triggered..").id("consumer-log")
				.to("file:C:/_test/_fuse").id("fileProducer");
	}

}
