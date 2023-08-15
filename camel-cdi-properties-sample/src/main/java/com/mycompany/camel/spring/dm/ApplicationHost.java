package com.mycompany.camel.spring.dm;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.mycompany.camel.spring.dm.RouteHandler.HelloRoute;

public class ApplicationHost {
	
	public static void main(String... args) throws InstantiationException, IllegalAccessException {
		CamelContext context = new DefaultCamelContext();
		context.addComponent("properties", new RouteHandler().properties(RouteHandler.DeltaSpikeParser.class.newInstance()));
		
		HelloRoute myRoute = new HelloRoute();

		// add our route to the CamelContext
		try {
			System.out.println("before adding route");
			context.addRoutes(myRoute);

			System.out.println("route added");
			context.start();
			System.out.println("context started");
			Thread.sleep(1000);

			// stop the CamelContext
			context.stop();
			System.out.println("context stopped");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
