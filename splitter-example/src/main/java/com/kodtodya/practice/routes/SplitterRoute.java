package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SplitterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:{{input.location}}?noop=true").id("file-consumer-component")
                .routeId("file-to-logger-route")
                // split the file content & tokenize using new-line('\n')
                .split().tokenize("\n").id("splitter-process")
                .to("log:myLogger").id("log-component");
    }

}
