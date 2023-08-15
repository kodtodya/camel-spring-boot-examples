package com.redhat.camel.blueprint.exception.handling;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

	/*public void configure() {

		String service = "jetty:http://localhost:8181/greetingService";
		
		from(service).process(new MyProcessor(){
		public void process(Exchange exchange) throws Exception {
			String message = exchange.getIn().getBody(String.class);

			System.out.println("exception excuted:" + message);

			exchange.getOut().setBody("exception- " + message);			
		}
		}).onException(Exception.class).to("mock:result");

	}*/
	
	
	public void configure() {

		String service = "jetty:http://localhost:8181/msgService";
		
		from(service).doTry().		
		process(new MyProcessor(){
		public void process(Exchange exchange) throws Exception {
			String message = exchange.getIn().getBody(String.class);

			System.out.println("inside the processor");

			exchange.getOut().setBody("msg - " + message);			
			throw new Exception("my custom exception");
		}
		}).to("mock:result")
		
		.doCatch(Exception.class).onWhen(exceptionMessage().contains("custom"))
        //.to("mock:catch")
        .to("file:/home/alele/_tech/testData/output")
        
        .doFinally()
        .to("mock:finally")
        
        .end();

	}

}