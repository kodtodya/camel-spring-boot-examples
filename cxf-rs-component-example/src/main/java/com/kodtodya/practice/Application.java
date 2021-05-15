package com.kodtodya.practice;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * A spring-boot application that includes a Camel route builder to setup the Camel routes
 */
@SpringBootApplication
public class Application {

    // must have a main method spring-boot can run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
/*
    @Bean("restService")
    public Server jaxrsServer() {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(EmployeeService.class);
        factoryBean.setAddress("http://localhost:8888/");
        return factoryBean.create();
    }*/


    /*@Bean
    public SpringJAXRSClientFactoryBean tsdbData()
    {
        SpringJAXRSClientFactoryBean springJAXRSClientFactoryBean = new SpringJAXRSClientFactoryBean();
        springJAXRSClientFactoryBean.setBeanId("restService");
        springJAXRSClientFactoryBean.setAddress("http://localhost:8888/employeeService");
        springJAXRSClientFactoryBean.setServiceClass(EmployeeServiceResource.class);
        springJAXRSClientFactoryBean.setLoggingFeatureEnabled(true);
        springJAXRSClientFactoryBean.setSkipFaultLogging(true);
        //springJAXRSClientFactoryBean.setProvider(new JacksonJsonProvider());
        return springJAXRSClientFactoryBean;
    }*/
}