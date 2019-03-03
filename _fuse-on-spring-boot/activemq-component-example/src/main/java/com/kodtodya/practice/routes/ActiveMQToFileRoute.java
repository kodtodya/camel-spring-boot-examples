package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ActiveMQToFileRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		ExecutorService executor = Executors.newFixedThreadPool(25);

		from("activemq:queue:myQ").id("amqConsumer").routeId("ActiveMQ-File-Route")
				.multicast().parallelProcessing()
				.executorService(executor)
				.throttle(25)
				.to("file:/home/kodtodya/Downloads/test").id("fileProducer")
				.to("log:myLogger");
	}

}
