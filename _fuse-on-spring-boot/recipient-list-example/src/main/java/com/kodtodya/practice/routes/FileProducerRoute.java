package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileProducerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:myDirectComponent").id("directConsumer")
                .recipientList(header("myHeader"), "#").parallelProcessing()
                .log("\n${body}\n")
                .to("file:{{output.location}}?fileName=myFile.one")
                .to("file:{{output.location}}?fileName=myFile.two")
                .to("file:{{output.location}}?fileName=myFile.three")
                .to("file:{{output.location}}?fileName=myFile.four")
                .to("file:{{output.location}}?fileName=myFile.five");
    }

}
