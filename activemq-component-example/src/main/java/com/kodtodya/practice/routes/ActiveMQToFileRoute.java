package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQToFileRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("activemq:queue:myQ").id("amqConsumer").routeId("ActiveMQ-File-Route")
				.log("ActiveMQ consumer triggered..").id("consumer-log")
				//.to("file:C:/_test/_fuse").id("fileProducer");
				.to("file:/home/kodtodya/Downloads/test").id("fileProducer");
	}

}
