package com.redhat.camel.blueprint.osgi.interfaces;

import java.io.IOException;

import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.NamingException;

public interface AMQMessageProducer {

	public void init(Context ctx, String queueName)throws NamingException, JMSException;
	public void send(String message) throws JMSException;
	public void close() throws JMSException ;
	public void readAndSend() throws IOException, JMSException;
	public void producerWorker() throws Exception;
}
