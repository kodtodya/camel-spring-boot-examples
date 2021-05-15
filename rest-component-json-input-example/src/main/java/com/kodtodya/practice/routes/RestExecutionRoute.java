package com.kodtodya.practice.routes;
import com.kodtodya.practice.beans.Customer;
import com.kodtodya.practice.services.PrimeNumberService;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestExecutionRoute extends RouteBuilder {

	@Autowired
	private PrimeNumberService service;

	@Override
	public void configure() throws Exception {

		onException(CamelExecutionException.class)
				.handled(true)
				.redeliveryDelay(2000)
				.maximumRedeliveries(5)
				// asynchronous redelivery
				.asyncDelayedRedelivery()
				// log the exeption details
				.to("log:exceptionLogger");

		// call the embedded rest service from the RestController
		restConfiguration()
				.component("servlet")
				.port(8080)
				.bindingMode(RestBindingMode.auto);

		JacksonDataFormat format = new JacksonDataFormat(Customer.class);
		format.setPrettyPrint(true);

		// actual rest service implementation
		rest()
				.post("/customer")
				.type(Customer.class)
				.consumes("application/json")
				.route()
				.to("direct:customer")
				.end();

		from("direct:customer")
				.inOnly()
				.log("Incoming Body is ${body}")
				.unmarshal(format)
				.log("Incoming Body after unmarshal is ${body}")
				.bean(this,"transformMessage")
				.log("Outgoing pojo Body is ${body}")
				.to("file:{{output.location}}");
	}

	public void transformMessage(Exchange exchange){
		Message in = exchange.getIn();
		Customer customer = in.getBody(Customer.class);

		StringBuilder response = new StringBuilder();
		response.append("id=" + customer.getId())
				.append("|name=" + customer.getName())
				.append("|address=" + customer.getAddress())
				.append("|payment-method=" + customer.getPaymentMethod());

		in.setBody(response);
	}
}