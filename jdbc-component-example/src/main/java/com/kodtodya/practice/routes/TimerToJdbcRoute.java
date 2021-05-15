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

		// event generator
		from("timer://jdbcTimer?period=1000&repeatCount=25").id("timerEndpoint")

				// insert record by randomly generating sample data
				.process(sqlProcessor).id("sql-processor")

				// send to database
				.to("jdbc:dataSource").id("jdbc-endpoint")

				// log statement
				.log("record inserted...");
	}

}
