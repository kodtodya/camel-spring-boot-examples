package org.mycompany.processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.telegram.model.IncomingMessage;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		IncomingMessage message = (IncomingMessage) exchange.getIn().getBody();
		exchange.getIn().setBody(message.getText());
		System.err.println("processor invoked");
	}
}