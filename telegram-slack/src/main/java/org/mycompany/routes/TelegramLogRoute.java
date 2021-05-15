package org.mycompany.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TelegramLogRoute extends RouteBuilder {

	public void configure() {

		from("telegram:bots/704793803:AAHXoYPbLalM0r9pnGbzus0HjhObnUK08ac").id("telegram-consumer")
		.log(LoggingLevel.INFO, "Message=>>> ${body}")
		.to("direct:slackDirect?block=true&timeout=5000").id("directProdcuer");
	}

}
