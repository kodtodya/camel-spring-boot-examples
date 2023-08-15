package com.redhat.camel.blueprint.jetty;

import org.apache.camel.Exchange;

import org.apache.camel.Processor;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

	public void configure() {

		from("jetty:http://localhost:8181/greetingService").process(

				new Processor() {

					@Override

					public void process(Exchange exchange) throws Exception {

						String message = exchange.getIn().getBody(String.class);

						System.out.println("Hello Mr :" + message);

						exchange.getOut().setBody("Hello world Mr " + message);

					}

				});

	}

}