package com.kodtodya.practice.routes;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(CamelExecutionException.class)
                .to("log:exceptionLogger");

        // read file content from input location
        from("file:{{input.file.location}}")
                .routeId("file-encryption-route")

                // convert it to string
                .convertBodyTo(String.class)

                // encrypt file content using PGP public key and user-id
                .marshal().pgp("{{encryption.public-key.path}}", "{{encryption.public-key.user-id}}")

                // write encrypted data to file at encrypted file location - check properties
                .to("file:{{encrypted.file.location}}?fileName=test-data.txt.pgp");
    }
}