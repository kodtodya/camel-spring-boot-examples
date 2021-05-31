package com.kodtodya.practice.routes;

import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileConsumerRoute extends RouteBuilder{

	@Value("${input.location}")
	//@PropertyInject("{{input.location}}")
	private String inputLocation;

	@Override
	public void configure() throws Exception {
		from("file:" + inputLocation + "?noop=true&initialDelay=2000&bufferSize=512").id("file-consumer")
				.streamCaching()
				// log statement
				.log("\n\nHi, You file content is in file consumer is>>\n\n ${body}")
				// send to direct component
				.to("direct:my-direct-component?timeout=5000&failIfNoConsumers=false").id("direct-producer");
	}

}
