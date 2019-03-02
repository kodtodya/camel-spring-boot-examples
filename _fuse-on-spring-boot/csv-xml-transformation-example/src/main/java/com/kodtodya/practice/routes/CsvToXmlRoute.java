package com.kodtodya.practice.routes;

import com.kodtodya.practice.processor.TransformationProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsvToXmlRoute extends RouteBuilder{

	@Autowired
	private TransformationProcessor processor;

	@Override
	public void configure() throws Exception {
		from("file:{{input.location}}?noop=true")
				.process(processor)
				.to("file:{{output.location}}?fileName=emp.xml");
	}
}
