package com.kodtodya.practice.broker;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class ActiveMQBroker {
	
	@Value("${spring.activemq.broker-url}")
	private String userPath;
	
	@Bean
	public BrokerService configureBroker() throws Exception
	{
	BrokerService broker = new BrokerService();
	TransportConnector connector = new TransportConnector();
    connector.setUri(new URI(userPath));
    broker.addConnector(connector);
    broker.setBrokerName("myBroker");
    broker.setPersistent(false);
    broker.setUseJmx(false);
    broker.start();
    return broker;
}
}
