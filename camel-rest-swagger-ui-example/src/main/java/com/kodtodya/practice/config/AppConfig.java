package com.kodtodya.practice.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.eclipse.jetty.server.handler.ResourceHandler;

@Component
public class AppConfig extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        // call the embedded rest service from the RestController
        restConfiguration()
                .component("jetty")

                //Swagger properties
                //.contextPath("/api") //base.path swagger property; use the mapping path set for CamelServlet

                //Enable swagger endpoint.
                .apiContextPath("/api-docs") //swagger endpoint path
                .apiContextRouteId("swagger") //id of route providing the swagger endpoint
                .apiContextListing(true)
                .enableCORS(true)

                .apiProperty("api.title", "Swagger UI for training related web services")
                .apiProperty("api.version", "1.0")
                .apiProperty("api.contact.name", "kodtodya-talks")
                .apiProperty("api.contact.email", "kodtodya.talks@gmail.com")
                .apiProperty("api.contact.url", "https://kodtodya.github.io")
                .apiProperty("schemes", "http,https")
                .apiProperty("host", "0.0.0.0:8888")
                .apiProperty("prettyPrint", "true")
                .apiProperty("handlers", "swaggerUIHandler")

                .host("0.0.0.0")
                .port(8888)
                .bindingMode(RestBindingMode.off);

        rest("/swagger")
                .produces("text/html")
                .get("/index.html")
                .responseMessage().code(200).message("Swagger UI").endResponseMessage()
                .to("direct:get-swagger-ui-path");

        from("direct:get-swagger-ui-path")
                .routeId("SwaggerUI")
                .setBody().simple("resource:classpath:/swagger-ui/index.html");
    }

    @Bean("swaggerUIHandler")
    public ResourceHandler buildResourceHandler() throws Exception {
            ResourceHandler rh = new ResourceHandler();
            rh.setResourceBase("META-INF/resources/webjars");
            return rh;
        }
}
