package com.redhat.camel.blueprint.osgi.interfaces;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.naming.Context;
import javax.naming.NamingException;

public interface AMQMessageConsumer extends MessageListener{

	public final static String JMS_FACTORY="ConnectionFactory";
    public final static String QUEUE="dynamicQueues/MyQ";
    public final static String USER="admin";
    public final static String PASSWORD="admin";
    
    public void onMessage(Message msg);
    public void init(Context ctx, String queueName) throws NamingException, JMSException;
    public void close()throws JMSException;
	public void consumerWorker() throws Exception;
    
}
