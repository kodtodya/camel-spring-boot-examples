package com.kodtodya.practice.routes;

import com.kodtodya.practice.beans.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;

@Component
public class CsvToXmlRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		BindyCsvDataFormat employeeDataFormat = new BindyCsvDataFormat(Employee.class);

		// read file
		from("file:{{input.location}}?noop=true")
				.log("file reading completed; now splitting data...")

				// split data from file content
				.split().tokenize(System.lineSeparator())
				// convert csv data into java object
				.unmarshal(employeeDataFormat)
				// setting employee name into exchange property
				// so that we can use it later for file-name
				.setProperty("employee-name", simple("${body.getName()}"))
				.log("employee csv record transformed to POJO for ${exchangeProperty.employee-name}...")
				// send for further processing
				.to("direct:employee-transformation");

		JaxbDataFormat employeeJaxbDataFormat = new JaxbDataFormat();
		employeeJaxbDataFormat.setContext(JAXBContext.newInstance(Employee.class));

		from("direct:employee-transformation")
				// converting employee POJO into XML
				.marshal(employeeJaxbDataFormat)
				.log("transformation completed for ${exchangeProperty.employee-name}...")
				// write xml to file
				.to("file:{{output.location}}?fileName=${exchangeProperty.employee-name}.xml");
	}
}
