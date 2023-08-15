package com.mycompany.camel.activemq.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCamelIntegration {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext springContext = new ClassPathXmlApplicationContext("spring.xml");
		
		FileWriterService fileService = springContext.getBean(FileWriterService.class);
		
		for(int i=0;i<=9;i++){
			fileService.writeMsg(springContext.getBean(MyMessage.class).getMessage());
		}

		System.out.println("wrote messages to disk");
	}

}
