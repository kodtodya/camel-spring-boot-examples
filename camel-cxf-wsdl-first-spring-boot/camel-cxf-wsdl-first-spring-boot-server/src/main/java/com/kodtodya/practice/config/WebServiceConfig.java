package com.kodtodya.practice.config;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cc.notsoclever.customerservice.CustomerService;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;
    
    //NOTE THE VALUE OF cxf.path in application.properties this leads to 
    //the URL of the soap service being of the form /service/CustomerServicePort

    @Bean
    public CxfEndpoint customerServiceEndpoint() {
    	
    	CxfEndpoint cxfEndpoint = new CxfEndpoint();
    	cxfEndpoint.setAddress("/CustomerServicePort");
    	cxfEndpoint.setServiceNameString("s:customer:customerServiceService");
    	cxfEndpoint.setServiceClass(CustomerService.class);
    	cxfEndpoint.setBus(bus);
        return cxfEndpoint;
    }
}
