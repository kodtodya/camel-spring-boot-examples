package com.kodtodya.practice.routes;

import com.kodtodya.practice.Application;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("unittest")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = Application.class)
// Put here to prevent Spring context caching across tests and test methods since some tests inherit
// from this test and therefore use the same Spring context.  Also because we want to reset the
// Camel context and mock endpoints between test methods automatically.
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FileProducerRouteTest {

    @Autowired
    private CamelContext context;

    @Value("${input.location}")
    private String inputLocation;

    @EndpointInject(uri = "mock:direct-endpoint")
    private MockEndpoint directMockEndpoint;

    @Autowired
    private ProducerTemplate producerTemplate;

    protected CamelContext createCamelContext() throws Exception{
        return context;
    }

    @Before
    public void setup() throws Exception {
        final RouteDefinition directConsumer = context.getRouteDefinition("direct-consumer");

        Assert.assertNotNull(directConsumer);

        directConsumer.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("file:*")
                        .skipSendToOriginalEndpoint()
                        .to(directMockEndpoint);
            }
        });
    }

    @Test
    public void test000() throws Exception {

        context.start();

        Exchange exchange = new DefaultExchange(context);
        exchange.getIn().setBody("test-data");

        directMockEndpoint.expectedMessageCount(1);

        Exchange result = producerTemplate.send("direct:my-direct-component", exchange);

        directMockEndpoint.assertIsSatisfied();

        Assert.assertEquals("test-data", result.getIn().getBody(String.class));

        context.stop();
    }
}