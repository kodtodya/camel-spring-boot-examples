package com.redhat.camel.blueprint.http;

import org.apache.camel.Processor;
import org.apache.camel.Exchange;

public class BookService implements Processor {
   public void process(Exchange exchange) throws Exception {
       // Grab the booked header value
       String bookId = (String) exchange.getIn().getHeader("bookId");
       // send a html response
       exchange.getOut().setBody("<html><body>Book " + bookId + " is Camel in Action.</body></html>");
   }
} 