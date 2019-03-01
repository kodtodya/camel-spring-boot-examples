package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SplitterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:{{input.location}}?noop=true").split().tokenize("\n").to("log:myLogger");
    }

}
