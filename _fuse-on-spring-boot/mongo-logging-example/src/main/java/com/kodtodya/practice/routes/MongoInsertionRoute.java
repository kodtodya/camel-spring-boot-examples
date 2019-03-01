package com.kodtodya.practice.routes;

import com.kodtodya.practice.processors.ContentProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoInsertionRoute extends RouteBuilder {

    @Autowired
    private ContentProcessor processor;

    @Override
    public void configure(){

        onException(Exception.class).to("log:exceptionLogger")
        .handled(true);


        from("timer://foo?period=1000&repeatCount=10").id("timerEndpoint")
                .process(processor)
                .log("${body} object insertion triggered...")
                .to("mongodb:mongoDB?database={{mongodb.database}}&collection={{mongodb.collection}}&operation=insert");
    }
}
