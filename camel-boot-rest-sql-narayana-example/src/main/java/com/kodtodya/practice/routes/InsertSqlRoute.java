package com.kodtodya.practice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kodtodya.practice.processor.SQLProcessor;

@Component
public class InsertSqlRoute extends RouteBuilder{

	@Autowired
	private SQLProcessor sqlProcessor;

	@Override
	public void configure() throws Exception {
		// direct consumer
		from("direct:insertEndpoint").id("insertEndpoint")

				// set body which is received in header
				.setBody().simple("${header.course}")

				// log statement
				.log("web service triggered and parameter is : ${header.course}")

				// prepare query
				.process(sqlProcessor).id("sqlProcessor")

				// transaction enabled
				.transacted()

				// send to database
				.to("sql:{{insert.query}}?dataSource=#dataSource")
				//.to("jdbc:dataSource").id("jdbcEndpoint")

				// log statement
				.log("record inserted...")

				// response body set
				.setBody().simple("${header.course} record inserted");

	}
	
}