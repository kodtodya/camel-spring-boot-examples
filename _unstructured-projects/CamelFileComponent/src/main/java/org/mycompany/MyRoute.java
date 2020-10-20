package org.mycompany;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		from("file://data/?fileName=test1.txt&charset=utf-8&noop=true")
        .to("file://data/?fileName=test2.txt&charset=utf-8");
	}	
}