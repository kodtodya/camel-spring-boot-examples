package com.kodtodya.practice.routes;

import com.kodtodya.practice.processor.SQLProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimerToJdbcRoute extends RouteBuilder{

	@Autowired
	private SQLProcessor sqlProcessor;

	@Override
	public void configure() throws Exception {
		// timer - event generator
		from("timer://jdbcTimer?period=1000&repeatCount=10").id("timer-endpoint")

				// create insert queries
				.process(sqlProcessor).id("sql-processor")

				// insert records to database using query
				.to("jdbc:dataSource").id("jdbc-endpoint")

				// log statement
				.log("record inserted...");
	}

}
