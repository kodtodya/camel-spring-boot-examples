# Fuse-7 Examples

## Fuse on Spring Boot

This folder contains all the examples(Standalone Spring Boot) which can be started standalone with below command.

```
  java -jar <application_jar_name.jar>
```

## Sequence of the examples for practical session:

1. timer-services-example (timer & direct-vm components)
2. quartz2-component-example (quartz2, stream, stream-file & log components)
3. seda-component-example (seda & log components)
4. file2-component-example (file & direct components)
5. http-component-example (http & log components)
6. rest-component-example (rest/servlet & direct components)
7. rest-jetty-component-example (rest/jetty & direct components)
8. rest-component-json-input-example (rest/servlet, file & direct components with json input)
9. splitter-example (file & log components and splitter EIP )
10. splitter-aggregator-example (timer & direct component and processor & splitter-aggregator EIP)
11. splitter-aggregator-file-example (file & direct component and splitter-aggregator EIP)
12. camel-ehcache-example (direct & ehcache components)
13. content-based-routing-example (file & direct components and content-based-routing & transform EIPs)
14. recipient-list-example (file & direct components and recipientList EIP)
15. csv-xml-transformation-example (file, log & direct and bindy, jaxb, marshalling, unmarshalling & splitting EIP)
16. activemq-component-example (file & activemq components and embedded broker, multicast, thread-pool, parallel-processing and throttler EIPs)
17. external-activemq-component-example (rest, file & direct components and external broker, multicast, thread-pool, parallel-processing and throttler EIPs)
18. jms-component-example (file & jms components and embedded broker, multicast, thread-pool, parallel-processing and throttler EIPs)
19. external-jms-component-example (file & jms components and external brokers with cluster, multicast, thread-pool, parallel-processing and throttler EIPs)
20. cxf-rs-comonent-example (cxf-rs component using jax-rs)
21. jdbc-component-example (timer & jdbc component and processor, embedded database)
22. sql-component-example (timer, sql, file & jdbc component and processor & embedded database)
23. camel-boot-rest-sql-narayana-example (direct, rest & sql component and processor & embedded database)
24. sql-component-postgres-example (timer & jdbc component and processor, postgres database)
25. kafka-component-example (netty-http, rest, kafka, file producer, throttler, exception handling, concurrentConsumers and toD)
26. encryption-decryption-example (pgp keys, marshalling-unmarshalling, file producer-consumer, encryption, decryption, exception handling, recipientList, Parallel processing)
27. camel-rest-swagger-ui-example (rest, swagger-ui)
28. camel-cxf-wsdl-first-spring-boot (SOAP, processor, cxf, server-client)

Not covered here:

- camel-spring-boot-xa-example
- camel-spring-boot-kafka-producer
- mongo-logging-example
- telegram-slack
