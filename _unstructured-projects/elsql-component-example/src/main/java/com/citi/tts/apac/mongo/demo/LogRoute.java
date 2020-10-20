package com.citi.tts.apac.mongo.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//ROUTE TO EXECUTE ELSQL FILE AND LOG DATA
@Component
public class LogRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("timer:foo?repeatCount=1").setHeader("eventId", constant(12))
		//.process("myProcessor")
		.to("elsql:projects:com/citi/tts/apac/mongo/demo/projects.elsql?dataSource=myDataSource")
		
		.to("log:stream")
	    .split(body()).streaming()
	        .to("log:row")
	    .end();
	}

}
