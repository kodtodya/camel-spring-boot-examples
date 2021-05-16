package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ServerCxfRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("cxf:bean:customerServiceEndpoint")
                .to("bean:customerServiceProcessor");
    }
}
