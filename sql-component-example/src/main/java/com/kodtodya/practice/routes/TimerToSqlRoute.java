package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerToSqlRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // timer - event generator
        from("timer:sqlTimer?period=60000&repeatCount=1&delay=15000")

                .log("---------------------------")

                // fetch records from database
                .to("sql:{{select.query}}?dataSource=#dataSource")

                // split every record
                .split(body())

                // convert body to string
                .convertBodyTo(String.class)

                // logger statement
                .log("${body}")

                // save the records to file
                .to("file:{{output.location}}")

                .log("---------------------------");

    }
}
