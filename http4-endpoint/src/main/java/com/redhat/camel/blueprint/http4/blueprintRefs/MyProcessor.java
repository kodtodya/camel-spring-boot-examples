package com.redhat.camel.blueprint.http4.blueprintRefs;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class MyProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		if (null != exchange) {
            Message out = exchange.getOut();
            int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
            System.out.println("Response: " + String.valueOf(responseCode));
        }
		
	}

}
