package com.redhat.camel.blueprint.exception.handling;

import org.apache.camel.Processor;
import org.apache.camel.Exchange;

public class MsgService implements Processor {
   public void process(Exchange exchange) throws Exception {
      
	   
	   // Grab the booked header value
       String msgId = (String) exchange.getIn().getHeader("msgId");
       // send a html response
      exchange.getOut().setBody("hardcoded msg"+msgId);
      System.err.println("exception in processor"); 
      throw new Exception("custom exception message");
        
   }
} 