package com.kodtodya.practice.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LineAggregationStrategy implements AggregationStrategy {

    Logger logger = LoggerFactory.getLogger(LineAggregationStrategy.class);

    // Iterations   | Old Exchange data         | New Exchange Data | return type |
    // Iteration-0  | NULL                      | line-1            | list        |
    // Iteration-1  | line-1                    | line-2            | list        |
    // Iteration-2  | line-1,line-2             | line-3            | list        |
    // Iteration-3  | line-1,line-2,line-3      | line-4            | list        |
    // Iteration-4  |line-1,line-2,line-3,line-4| line-5            | list        |
    //.... and so on..

    public Exchange aggregate(Exchange oldLineExchange, Exchange newLineExchange) {
        // newInMessage will always provide us next/newLine
        Message newInMessage = newLineExchange.getIn();
        // extracting actual string content from received line of data
        String newLineBody = newInMessage.getBody(String.class);
        ArrayList list = null;

        // very first time, oldLineExchange will be null
        // hence below if condition should run
        // setting up oldLineExchange = newLineExchange breaks the camel flow
        // because camel does not copy references of newLineExchanges
        if (oldLineExchange == null) {
            // first time there is no existing data to create aggregated copy of data
            // hence we are initializing ArrayList
            list = new ArrayList();
            // adding received data to list
            list.add(newLineBody);
            // setting list to body
            newInMessage.setBody(list);
            logger.info("==============================================");
            logger.info("Aggregate New Exchange Data: " + newLineBody);
            logger.info("==============================================");
            // sending it back as response
            return newLineExchange;
        } else {
            // second time onwards, this section will get executed
            // last execution's list will be received as IN message Type
            Message in = oldLineExchange.getIn();
            list = in.getBody(ArrayList.class);
            logger.info("--------------------------------------------");
            logger.info("Aggregate old Exchange Data: " + list.toString());
            logger.info("Aggregate new Exchange Data: " + newLineBody);
            logger.info("--------------------------------------------");
            // adding newly received Line to existing set of lines/ArrayList
            list.add(newLineBody);
            //returning exchange
            return oldLineExchange;
        }
    }
}