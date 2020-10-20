package org.mycompany;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("quartz2://myTimer?trigger.repeatInterval=2000&trigger.repeatCount=10").setBody().simple("the current time is ${header.fireTime}").to("stream:out");
	}

}
