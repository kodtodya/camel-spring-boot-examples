package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XADemoRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		from("timer:foo?period=20000&repeatCount=2").id("route-timer").routeId("xa-demo-route")
			.setBody().simple("Message creation: ${date:now:yyyy-MM-dd HH:mm:ss}")
			.to("kafka:{{kafka.topic}}").id("kafka-producer")
			.log("headers ${in.headers}; body: ${body}").id("kafka-producer-logger")
			.bean(null);
	}

}