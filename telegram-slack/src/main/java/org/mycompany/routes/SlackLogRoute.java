package org.mycompany.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.slack.SlackComponent;
import org.mycompany.processes.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SlackLogRoute extends RouteBuilder {
	
	@Autowired
	MessageProcessor msgProcessor;
	
	public void configure() {
		
		from("direct:slackDirect").id("directConsumer")
		.process(msgProcessor)
		//.setBody().simple("${body}")
		.log(LoggingLevel.INFO, "Messaging sending to Slack-> ${body}")
		.to("slack:@slackbot?iconEmoji=:camel:&username=kodtodya&webhookUrl=https://hooks.slack.com/services/TEZDVMR0Q/BF0CU9UQ5/XXXXXXXXXXXXX").id("slackProducer")
		.log(LoggingLevel.INFO, "Test Post posted to slack-${body}");
		
	}

}
