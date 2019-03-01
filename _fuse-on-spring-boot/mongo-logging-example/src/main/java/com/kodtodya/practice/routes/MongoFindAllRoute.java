package com.kodtodya.practice.routes;

import com.kodtodya.practice.beans.Customer;
import com.kodtodya.practice.processors.ContentProcessor;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoFindAllRoute extends RouteBuilder {

    @Autowired
    private ContentProcessor processor;

    @Override
    public void configure(){

        from("timer://foo?period=10000&repeatCount=1").id("timerFindEndpoint")
                .to("mongodb:mongoDB?database={{mongodb.database}}&collection={{mongodb.collection}}&operation=findAll")
        .log("Finding all mongo records \\n------------------------\\n ${body} \\n ------------------------------");
    }
}
