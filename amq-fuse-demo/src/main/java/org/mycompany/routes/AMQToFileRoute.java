package org.mycompany.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AMQToFileRoute extends RouteBuilder {

	public void configure() {

		from("{{amq.component.and.queue}}").id("amqConsumer")
		.log(LoggingLevel.INFO, "Read message from Queue. Message is -> ${body}")
		.choice()
			.when().xpath("/person/city = 'London'")
				.to("file:{{output.location}}/uk")
				.log(LoggingLevel.INFO, "Message written to File. Message is -> ${body}")
			.otherwise()
				.to("file:{{output.location}}/others")
				.log(LoggingLevel.INFO, "Message written to File. Message is -> ${body}");
	}

}
