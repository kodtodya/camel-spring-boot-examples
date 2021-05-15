# Fuse-7 Examples

## Fuse on Spring Boot

This folder contains all the examples(Standalone Spring Boot) which can be started standalone with below command.

```
  java -jar <application_jar_name.jar>
```

## Practical Part - Sequence:

1. timer-services-example (timer & direct-vm components)
2. quartz2-component-example (quartz2, stream, stream-file & log components)
3. seda-component-example (seda & log components)
4. file2-component-example (file & direct components)
5. http-component-example (http & log components)
6. rest-component-example (rest/servlet & direct components)
7. rest-jetty-component-example (rest/jetty & direct components)
8. splitter-example (file & log components and splitter EIP )
9. splitter-aggregator-example (timer & direct component and processor & splitter-aggregator EIP)
10. splitter-aggregator-file-example (file & direct component and splitter-aggregator EIP)
11. camel-ehcache-example (direct & ehcache components)
12. content-based-routing-example (file & direct components and content-based-routing & transform EIPs)
13. recipient-list-example (file & direct components and recipientList EIP)
14. csv-xml-transformation-example (file, log & direct and bindy, jaxb, marshalling, unmarshalling & splitting EIP)
15. activemq-component-example (file & activemq components and embedded broker, multicast, thread-pool, parallel-processing and throttler EIPs)
16. external-activemq-component-example (rest, file & direct components and external broker, multicast, thread-pool, parallel-processing and throttler EIPs)
17. jms-component-example (file & jms components and embedded broker, multicast, thread-pool, parallel-processing and throttler EIPs)
18. external-jms-component-example (file & jms components and external brokers with cluster, multicast, thread-pool, parallel-processing and throttler EIPs)
19. cxf-rs-comonent-example (cxf-rs component using jax-rs)
20. jdbc-component-example (timer & jdbc component and processor, embedded database)
21. sql-component-example (timer, sql, file & jdbc component and processor & embedded database)
22. camel-boot-rest-sql-narayana-example (direct, rest & sql component and processor & embedded database)
23. kafka-component-example (netty-http, rest, kafka, file producer, throttler, exception handling, concurrentConsumers and toD)

Not covered here:

- camel-spring-boot-xa-example
- camel-spring-boot-kafka-producer
- mongo-logging-example
- telegram-slack
