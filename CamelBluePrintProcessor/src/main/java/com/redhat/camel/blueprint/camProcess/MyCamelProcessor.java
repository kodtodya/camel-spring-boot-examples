package com.redhat.camel.blueprint.camProcess;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyCamelProcessor implements Processor{

	private MessageBean msgBean;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getIn().setBody("setting body in java code");
		
		//msgBean.setMsg("changed message set");
		
	}

	public void setMsgBean(MessageBean msgBean) {
		this.msgBean = msgBean;
	}

}
