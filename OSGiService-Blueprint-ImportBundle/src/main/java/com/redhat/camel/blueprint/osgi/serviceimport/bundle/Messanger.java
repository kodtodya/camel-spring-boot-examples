package com.redhat.camel.blueprint.osgi.serviceimport.bundle;

import com.redhat.camel.blueprint.osgi.interfaces.AMQMessageConsumer;
import com.redhat.camel.blueprint.osgi.interfaces.AMQMessageProducer;

public class Messanger {

	private AMQMessageProducer msgProducer;
	private AMQMessageConsumer msgConsumer;
	public AMQMessageProducer getMsgProducer() {
		return msgProducer;
	}
	public void setMsgProducer(AMQMessageProducer msgProducer) {
		this.msgProducer = msgProducer;
	}
	public AMQMessageConsumer getMsgConsumer() {
		return msgConsumer;
	}
	public void setMsgConsumer(AMQMessageConsumer msgConsumer) {
		this.msgConsumer = msgConsumer;
	}
	
	public void runProducer(){
		try {
			msgProducer.producerWorker();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runConsumer(){
		try {
			msgConsumer.consumerWorker();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
