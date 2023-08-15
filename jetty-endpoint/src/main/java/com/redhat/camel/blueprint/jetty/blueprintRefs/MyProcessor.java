package com.redhat.camel.blueprint.jetty.blueprintRefs;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		String message = exchange.getIn().getBody(String.class);

		System.out.println("Hello Mr :" + message);

		exchange.getOut().setBody("Hello world Mr " + message);
		
	}

}
