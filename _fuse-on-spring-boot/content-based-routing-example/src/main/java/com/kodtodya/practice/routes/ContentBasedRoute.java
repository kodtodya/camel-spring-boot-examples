package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ContentBasedRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:{{input.location}}?noop=true").split().tokenize("\n").to("direct:contentBasedRouting");

        //Content Based routing- Route the message based on the token it contains.
        from("direct:contentBasedRouting")
                .choice()
                .when(body().contains("Residential"))
                .to("file:{{output.location}}?fileName=residential.csv")
                .otherwise()
                .to("file:{{output.location}}?fileName=commercial.csv");
    }

}
