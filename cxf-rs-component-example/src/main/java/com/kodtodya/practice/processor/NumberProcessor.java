package com.kodtodya.practice.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class NumberProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String response = "Prime";
        int number = exchange.getIn().getBody(Integer.class);
        if (number <= 1) {
            response = "Not " + response;
        } else {
            for (int counter = 2; counter <= number / 2; counter++) {
                if (0 == number % counter) {
                    response = "Not " + response;
                    break;
                }
            }
        }
        exchange.getIn().setBody(number + " is " + response);
    }
}
