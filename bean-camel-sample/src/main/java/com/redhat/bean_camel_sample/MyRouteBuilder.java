package com.redhat.bean_camel_sample;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
    	
    	//timer:foo?repeatCount=1
    	//file:/home/alele/_tech/testData/input?noop=true
        from("file:/home/alele/_tech/testData/input?noop=true").log("inside router").process(new Processor(){
			@Override
			public void process(Exchange exchange) throws Exception {
				String existingBody = exchange.getIn().getBody(String.class);
				
				System.out.println("existing messagebody: "+existingBody);
				
				Message message = new MessageBean();
				
				String changedBody = message.printMessage("Changed body in processor");
				
				exchange.getOut().setBody(changedBody);
				
			}}).to("file:/home/alele/_tech/testData/output/");
    }

}
