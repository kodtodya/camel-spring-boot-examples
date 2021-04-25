package com.kodtodya.practice.routes;

import com.kodtodya.practice.strategy.LineAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SplitterRoute extends RouteBuilder {

    @Autowired
    private LineAggregationStrategy strategy;

    @Override
    public void configure() throws Exception {
        //in only
        from("file:src/main/resources/input?noop=true&initialDelay=2000&bufferSize=512")
                .split(bodyAs(String.class).tokenize("\n"))

                .aggregationStrategy(strategy)
                .log("data before sending to direct:processLine --> ${body}")
                .to("direct:processLine").end();

        from("direct:processLine")
                .log("Body in logger is: ${body}");
    }
}