package com.kodtodya.practice.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SQLProcessor implements Processor {

    private static int id = 0;

    @Override
    public void process(Exchange exchange) throws Exception {
    	
    	String course = exchange.getIn().getBody(String.class);
        String query = "INSERT INTO COURSES(ID, NAME) VALUES ("+ ++id +", '"+ course +"')";
        exchange.getIn().setBody(query);
    }
}