package com.redhat.camel.cassandra;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class MyProcessor implements Processor{

	Logger logger = Logger.getLogger(MyProcessor.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("inside processor - before changing body");
		exchange.getIn().setBody(exchange.getIn().getBody().toString());
		logger.info("body changed");
		
	}

}
