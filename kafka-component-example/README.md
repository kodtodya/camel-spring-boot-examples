# kafka-camel-example
## camel-blueprint-example
### We are covering below topics in this example:
- netty-http
- rest
- blueprint
- kafka producer
- kafka consumer
- file producer
- throttler
- exception handling
- concurrentConsumers
- toD

Please build and follow instructions:

** Build this application with `mvn clean install -DskipTests`

## Now, start the kafka broker on localhost:9092

- Steps are provided from Linux perspective(Ubuntu Fan)
- Download Kafka from Kafka official website
- Extract respective zip/tar/tgz to certain location and rename that folder as `_apache-kafka`
- Make sure you have java environment setup to JDK-11
- Create `data` directory inside `_apache-kafka` directory
- Create `zookeeper` and `kafka-logs` inside the `data` directory
- Go to `_apache-kafka/config/zookeeper.properties` and change config from `dataDir=/tmp/zookeeper` to `dataDir=<path_to_kafka_folder>/_apache-kafka/data/zookeeper`
- Go to `_apache-kafka/config/server.properties` and change config from `log.dirs=/tmp/kafka-logs` to `log.dirs=<path_to_kafka_folder>/_apache-kafka/data/kafka-logs`
- Open cmd/terminal and go to `_apache-kafka/bin` directory

* always make sure to start zookeeper first
```
$ ./zookeeper-server-start.sh -daemon ../config/zookeeper.properties
```
--> this will not print anything; you can verify that kafka server is started using below command:
```
$ netstat -anp | grep 2181
```
--> you can also go to `_apache-kafka/data/zookeeper` directory; you will observe new log directory created as 'version-*' (* represent any number)

* Now, start the kafka server:
```
$ ./kafka-server-start.sh -daemon ../config/server.properties
```
--> Even, this command will not print anything; please verify if kafka server has started with below command.
```
$ netstat -anp | grep 9092
```
--> this command will provide you pid (e.g.5368/java)

* Use above received pid to find the process as below:
```
$ ps -aux | grep <pid>
```

You can also verify the kafka logs in `_apache-kafka/data/kafka-logs` folder

** congrats, your kafka server is up and running.
-----------------------------------------------------------------------------------------------------------------------------

** create topic to test with below command
```
$ ./kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic fuse-demo
```
--> above command will create new topic named 'fuse-demo'

** validate the topic is created
```
$ ./kafka-topics.sh --list --bootstrap-server localhost:9092
```

** after valiation of topic creation, start console consumer to check if messages are reaching to kafka using below command
```
$ ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic fuse-demo --from-beginning
```

** after starting consumer on topic, start console producer to send messages on kafka topic using below command
```
./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic fuse-demo
```

## Now, it's time to test our app
```
 After running the example, check your console consumer; it should print the messages
```

### After deployment of bundle, please invoke below rest service with plain text sample body:

REST -> POST -> http://localhost:9999/kafka-fuse-demo/sendMessage
