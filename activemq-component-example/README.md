# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel, Spring Boot with embedded ActiveMQ broker.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message every second, and routes the message to ActiveMQ.
Then second route consumes messages from ActiveMQ and writes to new file each time at specified location.

### Building

The example can be built with

    mvn clean install


### Check Hawtio

All the resource utilization can be monitored using Hawtio

    http://localhost:8081/hawtio
