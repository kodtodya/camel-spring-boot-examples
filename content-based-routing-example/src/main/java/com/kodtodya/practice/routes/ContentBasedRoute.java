package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ContentBasedRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // file consumer
        from("file:{{input.location}}?noop=true")
                // split file for every new line
                .split().tokenize("\n")
                // send to content-based-routing
                .to("direct:content-based-routing");

        //content Based routing- Route the message based on the token it contains.
        from("direct:content-based-routing")
                // appending new line to body
                // so that if there is any existing file
                // newly appended data should get appended to new line
                .transform(body().append("\n"))
                // based on content we will decide where to send the data
                .choice()
                // if ${body}.contains('Residential')
                .when(body().contains("Residential"))
                // then send to {{output.location}}/residential.csv file
                // in case of fileExist=Append, pls refer to bug: https://issues.apache.org/jira/browse/CAMEL-14127
                .to("file:{{output.location}}?fileName=residential.csv&fileExist=Append&charset=utf-8")

                // else
                .otherwise()
                // then send to {{output.location}}/commercial.csv file
                // in case of fileExist=Append, pls refer to bug: https://issues.apache.org/jira/browse/CAMEL-14127
                .to("file:{{output.location}}?fileName=commercial.csv&fileExist=Append&charset=utf-8");
    }

}
