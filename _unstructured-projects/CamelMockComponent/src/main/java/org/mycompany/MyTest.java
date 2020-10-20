package org.mycompany;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class MyTest extends CamelTestSupport{
	
	@Override
	protected RoutesBuilder createRouteBuilder()throws Exception
	{
		return new MyRoute();
	}
	
	@Test
	public void checkJMS()throws Exception
	{
		MockEndpoint quote = getMockEndpoint("mock:quote");
		quote.expectedMessageCount(1);
		template.sendBody("jms:queue:quote", "Camel working");
		quote.assertIsSatisfied();
	}

}
