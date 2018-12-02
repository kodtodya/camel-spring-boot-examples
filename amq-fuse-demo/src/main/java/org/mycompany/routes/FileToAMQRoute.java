package org.mycompany.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileToAMQRoute extends RouteBuilder {

    public void configure() {

        from("file:{{input.location}}?noop=true").id("fileComponent")
        .log(LoggingLevel.INFO, "Read message from File. Message is -> ${body}")
        .to("{{amq.component.and.queue}}")
        .log(LoggingLevel.INFO, "Message sent to queue");
    }

}