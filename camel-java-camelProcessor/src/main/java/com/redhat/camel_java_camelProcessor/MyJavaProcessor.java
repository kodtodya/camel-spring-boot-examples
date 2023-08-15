package com.redhat.camel_java_camelProcessor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class MyJavaProcessor implements Processor{

	Logger logger = Logger.getLogger(MyJavaProcessor.class);
	public void process(Exchange exchange) throws Exception {
		logger.info("inside processor - before changing body");
		exchange.getIn().setBody("setting body in processor");
		logger.info("body changed");
	
	}
}
