# Spring-Boot Camel with Embedded ActiveMQ

This example demonstrates how you can use Apache Camel with Spring Boot in Java DSL with embedded ActiveMQ.

This example uses Spring Boot to configure a little application that includes a Camel route that read files and send over to activemq and again consuming from ActiveMQ and writing to File based on conditions.

### Building

The example can be built with

    mvn clean install

### Check Hawtio

All the resource utilization can be monitored using Hawtio

    http://localhost:8081/hawtio
