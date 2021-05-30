package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ActiveMQToFileRoute extends RouteBuilder {

	@Value("${spring.activemq.queue}")
	//@PropertyInject("{{spring.activemq.queue}}")
	private String queueName;

	@Override
	public void configure() throws Exception {

		// executor service implementation for thread-pooling
		ExecutorService executor = Executors.newFixedThreadPool(25);

		from("activemq:queue:" + queueName).id("amq-consumer").routeId("activemq-file-route")
				// Multicast EIP allows to route the same message to a multiple endpoints & process them in a different way
				// main difference between the Multicast and Splitter is that Splitter will split the message into several pieces
				// but the Multicast will not modify the request message.
				.multicast()

				// allowing route to have parallel processing
				.parallelProcessing()

				// applying thread pool over route
				.executorService(executor)

				// making sure that only 25 messages can be sent at a time
				.throttle(25)

				// write a message to file
				.to("file:{{output.location}}").id("file-producer")

				// print msg
				.to("log:myLogger");
	}
}
