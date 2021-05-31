package com.kodtodya.practice.routes;

import com.kodtodya.practice.Application;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ActiveProfiles("unittest")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
// Put here to prevent Spring context caching across tests and test methods since some tests inherit
// from this test and therefore use the same Spring context.  Also because we want to reset the
// Camel context and mock endpoints between test methods automatically.
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RestExecutionRouteTest {

    @Autowired
    private CamelContext context;

    @Autowired
    private ProducerTemplate producerTemplate;

    @EndpointInject(uri = "mock:direct-endpoint")
    private MockEndpoint directMockEndpoint;

    protected CamelContext createCamelContext() throws Exception{
        return context;
    }

    @Test
    public void test000() throws Exception {

        context.start();
        final RouteDefinition restRoute = context.getRouteDefinition("check-prime-service");
        Assert.assertNotNull(restRoute);
        restRoute.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("direct:prime")
                        .skipSendToOriginalEndpoint()
                        .to(directMockEndpoint);
            }
        });

        String restUri = "http4://localhost:8080/checkprime/11";
        Exchange response = producerTemplate.request(restUri, ex -> {
           ex.getIn().setHeader(Exchange.HTTP_METHOD, HttpMethod.GET);
           ex.getIn().setHeader(Exchange.HTTP_URI, restUri);
        });

        Assert.assertEquals(HttpMethod.GET, response.getIn().getHeader(Exchange.HTTP_METHOD));
        Assert.assertEquals(restUri, response.getIn().getHeader(Exchange.HTTP_URI));
        context.stop();
    }

    @Test
    public void test001() throws Exception {

        context.start();
        final RouteDefinition directPrimeRoute = context.getRouteDefinition("direct-prime");
        Assert.assertNotNull(directPrimeRoute);

        Exchange response = producerTemplate.request("direct:prime", ex -> {
            ex.getIn().setHeader("number", "11");
        });

        Assert.assertEquals("11", response.getIn().getBody(String.class));

        context.stop();
    }
}