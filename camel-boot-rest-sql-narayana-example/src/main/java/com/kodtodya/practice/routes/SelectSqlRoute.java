package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SelectSqlRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // direct consumer
        from("direct:fetchEndpoint").id("fetchDirectEndpoint")

                .transacted()

                // fetch the records from database
                .to("sql:{{select.query}}?dataSource=#dataSource")

                // log statement
                .log("\n---------------------------\n ${body} \n---------------------------\n")

                // set the fetched records to exchange body
                .setBody().simple("${body}");
    }
}