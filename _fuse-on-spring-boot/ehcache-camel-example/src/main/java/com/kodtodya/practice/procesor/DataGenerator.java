package com.kodtodya.practice.procesor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.ehcache.EhcacheConstants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataGenerator implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        // Generate the hashmap with data
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("id", "kodtodya");
        inputMap.put("company", "kodtodya-talks");
        inputMap.put("city", "Pune");

        exchange.getIn().setHeader("CamelEhcacheAction", EhcacheConstants.ACTION_PUT_ALL);
        exchange.getIn().setBody(inputMap);
    }
}
