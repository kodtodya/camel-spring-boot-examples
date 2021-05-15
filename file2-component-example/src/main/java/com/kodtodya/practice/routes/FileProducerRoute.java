package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileProducerRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("direct:myDirectComponent").id("directConsumer")
				// log statement
				.log("\n\nHi, Your file content in file producer is>>\n\n ${body}")
				// in case of fileExisti=Append, pls refer to bug: https://issues.apache.org/jira/browse/CAMEL-14127
				.to("file:{{output.location}}?fileName=camel-session-out.txt&fileExist=Append&charset=utf-8").id("fileProducer");
	}

}
