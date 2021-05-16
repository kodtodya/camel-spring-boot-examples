package com.kodtodya.cxf.wsdlfirst.client;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cc.notsoclever.customerservice.CustomerService;

@SpringBootApplication
public class Application {

    // must have a main method spring-boot can run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CxfEndpoint customerServiceEndpoint() {
    	
    	CxfEndpoint cxfEndpoint = new CxfEndpoint();
        //config property in src/main/resources/application.properties
    	cxfEndpoint.setAddress("{{customer-service.endpoint}}");
    	// 's' -> namespace of SOAP; ideally mandaotry
    	cxfEndpoint.setServiceNameString("s:customer:customerServiceService");
    	cxfEndpoint.setServiceClass(CustomerService.class);
        return cxfEndpoint;
    }
}
