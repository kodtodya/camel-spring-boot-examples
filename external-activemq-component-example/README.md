# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel, Spring Boot with embedded ActiveMQ broker.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message every second, and routes the message to ActiveMQ.
Then second route consumes messages from ActiveMQ and writes to new file each time at specified location.

### Building

#### Steps to get ready environment

1. Download the activemq from https://activemq.apache.org/components/classic/download/
   (current version is 5.16.2; at the time of you run if this version is changed, pls download it and make sure you are changing the <activemq.version> in pom.xml accordingly)
   

2. Extract it to some location


3. change directory to respective installation and go to bin folder


4. use ```./activemq start``` command for starting server on linux(you may need to figure out for windows)


5. check if your activemq-broker is really started using ```netstat -anp | grep 61616``` command on linux


6. open activemq console using steps

## Testing the Installation
### Using the administrative interface

- Open the administrative interface

```  
    URL: http://127.0.0.1:8161/admin/
    Login: admin
    Passwort: admin
```  

## Steps in Activemq web console

- Navigate to “Queues”
- Add a queue name and click create
- Send test message by clicking on “Send to”   

## Application build steps

The example can be built with

```
    mvn clean install
```

## How to test application

- After application build, run it

- open POSTMAN tool for invoking rest service written to send message to activemq

- POST -> http://localhost:8080/sendmsg

- Send request and expect response in plain text

- Also, check if file has been created containing same message inside the output folder inside current folder

### Check Hawtio

All the resource utilization can be monitored using Hawtio
```
    http://localhost:8081/hawtio
```

### Stop the activemq broker

```
    ./activemq stop
```
Cheers!!!
