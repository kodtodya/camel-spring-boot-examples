package com.kodtodya.practice;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kodtodya.practice"})
public class RestSqlNarayanaDemo {

    public static void main(String[] args) {
        SpringApplication.run(RestSqlNarayanaDemo.class, args);
    }
    
    @Bean
    public ServletRegistrationBean camelServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet());
        registration.setName("CamelServlet");
        return registration;
    }

}