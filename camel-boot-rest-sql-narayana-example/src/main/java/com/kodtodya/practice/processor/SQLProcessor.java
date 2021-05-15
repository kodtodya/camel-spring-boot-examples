package com.kodtodya.practice.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SQLProcessor implements Processor {

    private static int id = 0;

    @Override
    public void process(Exchange exchange) throws Exception {

        String course = exchange.getIn().getBody(String.class);
        Map<String, Object> sessionMap = new HashMap<>();
        sessionMap.put("id", ++id);
        sessionMap.put("course", course);
        exchange.getIn().setBody(sessionMap);
    }
}