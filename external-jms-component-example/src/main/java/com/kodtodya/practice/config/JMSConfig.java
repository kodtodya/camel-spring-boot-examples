package com.kodtodya.practice.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.ConnectionFactory;

@EnableJms
@Configuration
public class JMSConfig {

    @Value("${spring.jms.queue}")
	public String DEFAULT_QUEUE;
    
    @Value("${spring.jms.broker-url}")
    private String BROKER_URL;
    
    @Value("${spring.jms.user}")
    private String BROKER_USERNAME;

    @Value("${spring.jms.password}")
    private String BROKER_PASSWORD;

    @Bean
	public ActiveMQConnectionFactory connectionFactory(){
	    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	    connectionFactory.setBrokerURL(BROKER_URL);
	    connectionFactory.setPassword(BROKER_USERNAME);
	    connectionFactory.setUserName(BROKER_PASSWORD);
	    return connectionFactory;
	}

	@Bean("jms")
	public JmsComponent jmsComponent() {
		ConnectionFactory factory = this.connectionFactory();
		JmsComponent jms = new JmsComponent();
		jms.setConnectionFactory(factory);
		return jms;
	}
}