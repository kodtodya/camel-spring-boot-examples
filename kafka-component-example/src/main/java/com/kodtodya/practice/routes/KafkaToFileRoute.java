package com.kodtodya.practice.routes;

import com.kodtodya.practice.processor.KafkaMsgProcessor;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaToFileRoute extends RouteBuilder {

    @Autowired
    private KafkaMsgProcessor printEvents;

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        // read messages from kafka with 25 concurrent consumers & send to file
        from("{{kafka.component.uri}}&consumerStreams=25")
                // newly appended data should get appended to new line
                .transform(body().append("\n"))
                .log("Kafka Consumer Topic Name: ${headers[kafka.TOPIC]}")
                .log("Kafka Consumer Partition: ${headers[kafka.PARTITION]}")
                .log("Kafka Consumer Offset: ${headers[kafka.OFFSET]}")
                .log("Kafka Consumer Key: ${headers[kafka.KEY]}")
                // in case of fileExist=Append, pls refer to bug: https://issues.apache.org/jira/browse/CAMEL-14127
                .to("file:{{file.output.location}}?fileName={{output.file-name}}&fileExist=Append&charset=utf-8");
    }
}