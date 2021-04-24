# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel with Spring Boot.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message every 5th second, and routes the message to a log.

### Building

The example can be built with

    mvn clean package

## Build Docker/S2I image


    mvn oc:resource oc:build


## Deploy to openshift-4.x


    mvn oc:deploy


## Check Hawtio

All the resource utilization can be monitored using Hawtio

    http://localhost:8081/hawtio
