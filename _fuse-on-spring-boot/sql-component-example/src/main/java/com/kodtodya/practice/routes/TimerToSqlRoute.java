package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerToSqlRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:sqlTimer?period=5000&repeatCount=5")
                .to("sql:SELECT * FROM ORGANIZATIONS ORDER BY ID?dataSource=#dataSource")
                .log("\n---------------------------\n ${body} \n---------------------------\n");
    }
}
