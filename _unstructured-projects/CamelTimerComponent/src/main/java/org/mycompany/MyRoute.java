package org.mycompany;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("timer://myTimer?period=2000")
		.setBody().simple("Current time is ${header.firedTime}")
		.to("stream:out");
	}

}
