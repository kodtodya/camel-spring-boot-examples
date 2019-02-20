package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StreamFileToLogger extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		/*from("stream:file?fileName=C:/_testing/_bdd/_concordeBDD/logs/Concorde-BDD.log&scanStream=true&scanStreamDelay=1000").id("streamFileConsumer")
				.to("log:myLogger").id("streamLogger");*/
	}
}
