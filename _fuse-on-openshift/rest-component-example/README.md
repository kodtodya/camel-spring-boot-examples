# Spring-Boot Camel QuickStart [rest-dsl-servlet-component]

This example demonstrates how you can use Apache Camel, Spring Boot along for writing rest service.

### Building

The example can be built with

    mvn clean package -DskipTests

    OR

    mvn clean package


## Deploy to openshift-4.x


    mvn oc:resource oc:deploy


## Check Hawtio

All the resource utilization can be monitored using Hawtio:

http://localhost:8080/actuator/hawtio

If you have changed config in properties file for Hawtio, all the resource utilization can be monitored using Hawtio:

http://localhost:8080/hawtio


### Check Web-service

If you are running in local, please invoke: http://localhost:8080/checkprime/11

