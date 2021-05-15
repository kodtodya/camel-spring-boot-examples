# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel with Spring Boot.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message to ActiveMQ broker through JMS component and reads again and then write to file on file system.

### Building

The example can be built with

    mvn clean install

## Check Hawtio

All the resource utilization can be monitored using Hawtio

```
    http://localhost:8081/hawtio
```