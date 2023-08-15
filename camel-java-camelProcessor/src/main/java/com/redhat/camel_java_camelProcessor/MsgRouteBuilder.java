package com.redhat.camel_java_camelProcessor;

import org.apache.camel.builder.RouteBuilder;

public class MsgRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		MyJavaProcessor myProcessor = new MyJavaProcessor();
		from("timer:foo?repeatCount=1").process(myProcessor).log("adding route").//processRef("myProcessor").
        to("mock:result");
		
	}

	
}
