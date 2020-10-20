package org.mycompany;

import java.io.File;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class FirstTest extends CamelTestSupport{
	
	@Override
	protected RoutesBuilder createRouteBuilder()throws Exception
	{
		return new MyRoute();
	}
	
	@Test
	public void checkFile() throws InterruptedException
	{
		Thread.sleep(5000);
		File file = new File("data/output");
		assertTrue(file.isDirectory());
		assertEquals(1, file.listFiles().length);
	}

}
