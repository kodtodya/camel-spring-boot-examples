package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileConsumerRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:{{input.location}}?noop=true&initialDelay=2000&bufferSize=512").id("fileConsumer")
		
		.log("\n\nHi, Your file content is in file consumer is>>\n\n${body}")
		
		.to("direct:myDirectComponent?timeout=5000&failIfNoConsumers=false").id("directProducer");		
	}

}
