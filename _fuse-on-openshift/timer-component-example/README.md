# Spring-Boot & Camel Example - [timer-direct-vm-log]

This example demonstrates how you can use timer, direct-vm and log components in Spring boot.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message and routes the message to a log.


### Building

The example can be built with

    mvn clean package -DskipTests

    OR

    mvn clean package


## Build Docker/S2I image


    mvn oc:resource oc:build


## Deploy to openshift-4.x


    mvn oc:deploy


## Check Hawtio

All the resource utilization can be monitored using Hawtio:

http://localhost:8080/actuator/hawtio

If you have changed config in properties file for Hawtio, all the resource utilization can be monitored using Hawtio:

http://localhost:8080/hawtio