package com.kodtodya.practice.routes;

import com.kodtodya.practice.beans.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TransformDslRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		from("file:{{input.location}}")
				.marshal(Employee.class)
				.transform().body()
				.to("log:out");
	}

}
