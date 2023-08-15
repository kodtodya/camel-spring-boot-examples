package com.redhat.camel.blueprint.exception.handling;

import org.apache.camel.Processor;
import org.apache.camel.Exchange;

public class BookService implements Processor {
   public void process(Exchange exchange) throws Exception {
      
	   
	   // Grab the booked header value
       String bookId = (String) exchange.getIn().getHeader("bookId");
       // send a html response
      exchange.getOut().setBody("<html><body>Book " + bookId + " is Camel in Action.</body></html>");
      System.err.println("exception in processor"); 
      throw new Exception("exception in processor");
      
   /*   for(int i=1;i<=10;i++){
    	 if(i==5){
    		 throw new Exception("breaking exception");
    	 }
    	  System.out.println(i);
      }*/
      
   }
} 