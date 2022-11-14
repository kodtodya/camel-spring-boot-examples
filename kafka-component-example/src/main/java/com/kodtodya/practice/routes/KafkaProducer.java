package com.kodtodya.practice.routes;

import com.kodtodya.practice.processor.KafkaMsgProcessor;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer extends RouteBuilder {

    @Autowired
    private KafkaMsgProcessor printEvents;

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exception-logger");

        from("timer:my-timer?period=60000")
                .routeId("timer-to-kafka-route")
                .setBody().simple("Current time is: ${date:now:yyyy-MM-dd HH:mm:ss}")
                // Key of the message
                .setHeader(KafkaConstants.KEY, constant("fuse-demo"))
                // dynamic routing
                .to("kafka:{{kafka.component.uri}}")
                .log("Message sent to kafka with headers ${in.headers}; body: ${body}").id("kafka-producer-logger")
                .bean(printEvents);

    }
}