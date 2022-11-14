package com.kodtodya.practice.routes;

import com.kodtodya.practice.processor.KafkaMsgProcessor;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer extends RouteBuilder {

    @Autowired
    private KafkaMsgProcessor printEvents;

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exception-logger");

        // read messages from kafka with 25 concurrent consumers & send to file
        from("kafka:{{kafka.component.uri}}&consumersCount=1")
                // newly appended data should get appended to new line
                .transform(body().append("\n"))
                .log("---------------- Kafka Consumer - Message received - logging starts ----------------")
                .log("Kafka Consumer body received: ${body}")
                .log("Kafka Consumer Topic Name: ${headers[kafka.TOPIC]}")
                .log("Kafka Consumer Partition: ${headers[kafka.PARTITION]}")
                .log("Kafka Consumer Offset: ${headers[kafka.OFFSET]}")
                .log("Kafka Consumer Key: ${headers[kafka.KEY]}")
                .log("---------------- Kafka Consumer - Message received - logging ends ----------------");
    }
}