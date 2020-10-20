package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DirectToSqlRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:fetchEndpoint").id("fetchDirectEndpoint")
                .to("sql:SELECT * FROM COURSES ORDER BY ID?dataSource=#dataSource")
                .log("\n---------------------------\n ${body} \n---------------------------\n")
                .setBody().simple("${body}");
    }
}