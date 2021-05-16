# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel, Spring Boot along with rest post service with json input.

### Building

The example can be built with

    mvn clean install

### Test rest service

Invoke rest service using below URL. Feel free to update the input number.

    POST URL: localhost:8080/customer
    RAW Body with JSON(application/json) type: {"id":"1","name":"general electric","address":"united states","paymentMethod":"cheque"}

