package com.kodtodya.practice.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaMsgProcessor implements Processor {

	Logger logger = LoggerFactory.getLogger(KafkaMsgProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		List<RecordMetadata> recordMetaData = 
				(List<RecordMetadata>) exchange.getIn()
				.getHeader(KafkaConstants.KAFKA_RECORDMETA);
		
		for(RecordMetadata meta: recordMetaData) {
			logger.info("----------------- Kafka message processing started -----------------");
			logger.info("offset is:" +meta.offset());
			logger.info("partition is:" +meta.partition());
			logger.info("value size is:" +meta.serializedValueSize());
			logger.info("----------------- Kafka message processing ends here -----------------");
		}
	}
}
