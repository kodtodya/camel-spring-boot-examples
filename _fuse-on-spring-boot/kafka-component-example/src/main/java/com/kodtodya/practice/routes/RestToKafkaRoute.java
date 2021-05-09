package com.kodtodya.practice.routes;

import com.kodtodya.practice.processor.KafkaMsgProcessor;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestToKafkaRoute extends RouteBuilder {

    @Autowired
    private KafkaMsgProcessor printEvents;

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        restConfiguration()
                .component("netty-http")
                .host("0.0.0.0")
                .port(9999)
                .bindingMode(RestBindingMode.auto);

        // use the rest DSL to define the rest services
        rest("/kafka-fuse-demo")
                .post("/sendMessage")
                .consumes("application/text")
                .id("kafka-rest-service")
                .to("direct:myDirectKafkaComponent");

        // send message to jms server
        from("direct:myDirectKafkaComponent")
                // Key of the message
                .setHeader(KafkaConstants.KEY, constant("fuse-demo"))
                // dynamic routing
                .toD("{{kafka.component.uri}}")
                .log("headers ${in.headers}; body: ${body}").id("kafka-producer-logger")
                .bean(printEvents);

    }
}