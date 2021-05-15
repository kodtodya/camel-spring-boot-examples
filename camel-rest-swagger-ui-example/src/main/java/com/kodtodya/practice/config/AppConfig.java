package com.kodtodya.practice.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class AppConfig extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // call the embedded rest service from the RestController
        restConfiguration()
                .component("servlet")

                //Swagger properties
                .contextPath("/api") //base.path swagger property; use the mapping path set for CamelServlet

                //Enable swagger endpoint.
                .apiContextPath("/swagger") //swagger endpoint path
                .apiContextRouteId("swagger") //id of route providing the swagger endpoint

                .apiProperty("api.title", "Swagger UI for training related web services")
                .apiProperty("api.version", "1.0")
                .apiProperty("api.contact.name", "kodtodya-talks")
                .apiProperty("api.contact.email", "kodtodya.talks@gmail.com")
                .apiProperty("api.contact.url", "https://kodtodya.github.io")
                .apiProperty("host", "0.0.0.0") //by default 0.0.0.0
                .apiProperty("port", "8080")
                .apiProperty("schemes", "")
                .apiProperty("cors", "true")

                .port(8080)
                .bindingMode(RestBindingMode.auto);
    }

    @Controller
    public class SwaggerConfig {
        @RequestMapping("/swagger-ui")
        public String redirectToUi() {
            return "redirect:/webjars/swagger-ui/index.html?url=/camel-demo/api/swagger&validatorUrl=";
        }
    }
}
