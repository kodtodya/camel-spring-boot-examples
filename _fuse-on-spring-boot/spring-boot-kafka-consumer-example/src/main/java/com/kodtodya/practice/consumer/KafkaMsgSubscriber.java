package com.kodtodya.practice.consumer;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaMsgSubscriber {
	
	@Value("${kafka-group}")
	private String kafkaGroup;
	 
	private CountDownLatch latch = new CountDownLatch(3);
	
    @KafkaListener(topics = "${topic.name}", containerFactory = "kafkaListenerContainerFactory", topicPartitions = @TopicPartition(topic = "${topic.name}", partitions = { "0" }))
    public void listenGroupFoo(String message) {
        System.out.println("Received Messasge in group '"+kafkaGroup+"': " + message);
        latch.countDown();
    }

}
