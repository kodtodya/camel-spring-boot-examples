package com.redhat.camel_java_camelProcessor;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.Logger;

public class CamelProcessorHost {

	private static Logger logger = Logger.getLogger(CamelProcessorHost.class);
	public static void main(String[] args) {
	       // create CamelContext
        CamelContext context = new DefaultCamelContext();
        
        MsgRouteBuilder msgRouteBuilder = new MsgRouteBuilder();

         // add our route to the CamelContext
        try {
        	logger.info("before adding route");
        	context.addRoutes(msgRouteBuilder);
        	
			/*context.addRoutes(new RouteBuilder() {
			    public void configure() {
			        from("timer:foo?repeatCount=1").process(new MyJavaProcessor()).log("adding route").
			        to("mock:result");
			    }
			});*/
			logger.info("route added");
			context.start();
	        logger.info("context started");
	        Thread.sleep(1000);

	        
	        // stop the CamelContext
	        context.stop();
	        logger.info("context stopped");			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
       
    }

}
