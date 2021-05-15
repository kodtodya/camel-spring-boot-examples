package com.kodtodya.practice.routes;

import com.kodtodya.practice.strategy.LineAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SplitterRoute extends RouteBuilder {

    @Autowired
    private LineAggregationStrategy lineAggregationStrategy;

    @Override
    public void configure() throws Exception {
        // File consumer
        from("file:{{input.location}}?{{file-component.query-params}}")
                // split the file based on new line
                .split(bodyAs(String.class).tokenize("\n"))

                // aggregate the all line after processing
                .aggregationStrategy(lineAggregationStrategy)
                .log("data before sending to direct:processLine --> ${body}")

                // process every line
                .to("direct:processLine").end();

        // separate line data processor route
        from("direct:processLine")
                .log("Body in logger is: ${body}");
    }
}