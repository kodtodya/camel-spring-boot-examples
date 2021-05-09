# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel, Spring Boot along with embedded H2 database.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message every second 25 times, and routes the message to a H2 database.

### Building

The example can be built with

    mvn clean install

###Check Hawtio

All the resource utilization can be monitored using Hawtio

    http://localhost:8081/hawtio


### Check Database

Connect to H2 database and check your records

    http://localhost:8080/h2-console

After opening it in browser, use below params:

```
    Saved Settings: Generic H2 (Embedded)
    Settings: Generic H2 (Embedded)
    
    Driver Class: org.h2.Driver
    JDBC URL: jdbc:h2:mem:mydb
    User Name: sa
    Password:     
```
Click on connect and open `ORGANISATIONS` table or run `SELECT * FROM ORGANIZATIONS` query.

