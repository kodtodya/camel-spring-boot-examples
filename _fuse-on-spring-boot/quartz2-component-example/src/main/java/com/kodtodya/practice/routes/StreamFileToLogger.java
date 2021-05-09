package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StreamFileToLogger extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		/*from("stream:file?fileName={{input.location}}/{{input.file.name}}&scanStream=true&scanStreamDelay=1000")
				.id("streamFileConsumer").routeId("stream-file-to-logger-route")
				.to("log:myLogger").id("streamLogger");*/
	}
}
