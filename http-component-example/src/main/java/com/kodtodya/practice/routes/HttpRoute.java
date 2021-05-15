package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HttpRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer:myTimer?period=2000&repeatCount=1")
                .to("http4://www.google.com/search?q=kodtodya")
                .log("content received from https invocation is:\n\n\n${body}");
    }
}