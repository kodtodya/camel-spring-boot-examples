package com.redhat.camel.blueprint.osgi.service;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.redhat.camel.blueprint.osgi.interfaces.AMQMessageProducer;

public class AMQMessageProducerImpl implements AMQMessageProducer {

	public final static String JMS_FACTORY="ConnectionFactory";
    public final static String QUEUE="dynamicQueues/MyQ";
    public final static String USER="admin";
    public final static String PASSWORD="admin";

    private QueueConnectionFactory qconFactory;
    private QueueConnection qcon;
    private QueueSession qsession;
    private QueueSender qsender;
    private Queue queue;
    private TextMessage msg;

    public void init(Context ctx, String queueName)throws NamingException, JMSException
      {
          qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
          qcon = qconFactory.createQueueConnection(USER,PASSWORD);
          qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
          queue = (Queue) ctx.lookup(queueName);
          qsender = qsession.createSender(queue);
          msg = qsession.createTextMessage();
          qcon.start();
      }

    public void send(String message) throws JMSException 
      {
                 msg.setText(message);
                 qsender.send(msg);
      }

    public void close() throws JMSException 
      {
          qsender.close();
          qsession.close();
          qcon.close();
      }

    public void producerWorker() throws Exception 
      {
          InitialContext context = new AMQJNDIResolverImpl().getInitialContext("tcp://localhost:61616");
          init(context, QUEUE);
          readAndSend();
          close();
      }

    public void readAndSend() throws IOException, JMSException
      {
           //String line="Test Message Body with counter = ";
           /*BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
           boolean readFlag=true;
           System.out.print("\n\tStart Sending Messages (Enter QUIT to Stop):\n ");*/
    	String[] messages = {"first message","second message","third message","fourth message", "fifth message"};
           
    	for(String msg:messages){
    		send(msg);
    		System.out.println(msg);
    	}
    	System.out.println("5 messages sent");
      }
}
