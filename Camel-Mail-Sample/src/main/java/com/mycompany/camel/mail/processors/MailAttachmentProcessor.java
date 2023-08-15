package com.mycompany.camel.mail.processors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class MailAttachmentProcessor implements Processor {
	
	@Override
	public void process(Exchange exchange) throws Exception {
		Message out=exchange.getOut();
		out.setBody(exchange.getIn().getBody());
		System.out.println("body set to out message..");
		out.addAttachment("Email-Attachment", new DataHandler(new FileDataSource("/home/alele/_tech/_workNotes/myfile")));
		System.out.println("header set to out message..");
	}

}
