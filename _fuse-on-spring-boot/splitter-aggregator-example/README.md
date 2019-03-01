# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel, Spring Boot along with embedded H2 database.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message every second 25 times, and routes the message to a H2 database.

### Building

The example can be built with

    mvn clean install

###Check Hawtio

All the resource utilization can be monitored using Hawtio

    http://localhost:8081/hawtio


###Check Hawtio

Connect to H2 database and check your records

    http://localhost:8080/h2-console