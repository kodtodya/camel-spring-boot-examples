package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kodtodya.practice.processor.SQLProcessor;

@Component
public class DirectToJdbcRoute extends RouteBuilder{

	@Autowired
	private SQLProcessor sqlProcessor;

	@Override
	public void configure() throws Exception {
		from("direct:insertEndpoint").id("insertEndpoint")
			.transacted()
			.setBody().simple("${header.course}")
			.log("web service triggered and parameter is : ${header.course}")
			.process(sqlProcessor).id("sqlProcessor")
			.to("jdbc:dataSource").id("jdbcEndpoint")
			.log("record inserted...")
			.setBody().simple("${header.course} record inserted");

	}
	
}