package com.kodtodya.practice.processors;

import java.security.SecureRandom;

import com.kodtodya.practice.beans.Customer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;

@Component
public class ContentProcessor implements Processor{

	Logger logger = Logger.getLogger(ContentProcessor.class);
	
	public void process(Exchange exchange) throws Exception {
		logger.info("inside processor - before changing body");
		
		Customer customer = new Customer(getId(10),getName(3), getName(5));
		
		exchange.getIn().setBody(customer);
		
		logger.info("body changed");
	
	}
	
	private static String getName(int length){
		String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer randStr = new StringBuffer(length);
		SecureRandom secureRandom = new SecureRandom();
		for( int i = 0; i < length; i++ ) 
			randStr.append( CHAR_LIST.charAt( secureRandom.nextInt(CHAR_LIST.length()) ) );

		return randStr.toString().toUpperCase();
   }
	
	
	private static String getId(int length){
		String CHAR_LIST = "1234567890";
		StringBuffer randStr = new StringBuffer(length);
		SecureRandom secureRandom = new SecureRandom();
		for( int i = 0; i < length; i++ ) 
			randStr.append( CHAR_LIST.charAt( secureRandom.nextInt(CHAR_LIST.length()) ) );

		return randStr.toString();
   }
}