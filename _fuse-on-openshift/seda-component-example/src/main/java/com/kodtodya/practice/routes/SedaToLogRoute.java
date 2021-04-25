package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SedaToLogRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("seda:testSedaEndpoint?concurrentConsumers=5").id("sedaConsumer").routeId("Seda-Log-Route")
				.to("log:sedaLogger").id("sedalogger");
	}

}
