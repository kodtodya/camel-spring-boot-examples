package com.kodtodya.practice.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DecryptionRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .handled(true)
                .maximumRedeliveries(2)
                .redeliveryDelay(5000)
                .asyncDelayedRedelivery()
                .to("log:exceptionLogger");

        // read encrypted file from encrypted input file location
        from("file:{{encrypted.file.location}}?fileName=test-data.txt.pgp&noop=true")
                .routeId("file-decryption-route")

                // decrypt file content using PGP private key, username and password
                .unmarshal().pgp("{{encryption.private-key.path}}",
                "{{encryption.private-key.user-id}}",
                "{{encryption.private-key.password}}")

                // send it to file producer and logger
                .recipientList(constant("direct:directFileProducer,direct:directLogger"))
                .parallelProcessing();

        // file-producer
        from("direct:directFileProducer")
                .to("file:{{decrypted.file.location}}?fileName=myFile.txt");

        // logger
        from("direct:directLogger")
                .to("log:myLogger");
    }
}