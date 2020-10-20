package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class FileProcessor implements Processor{

	@Override
	public void process(Exchange arg0) throws Exception {
		// TODO Auto-generated method stub
		String message = "Processed message";
		arg0.getIn().setBody(message);
	}

}
