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
@SpringBootTest(classes = Application.class)
// Put here to prevent Spring context caching across tests and test methods since some tests inherit
// from this test and therefore use the same Spring context.  Also because we want to reset the
// Camel context and mock endpoints between test methods automatically.
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FileConsumerRouteTest {

    @Autowired
    private CamelContext context;

    @Value("${input.location}")
    private String inputLocation;

    @Autowired
    private ConsumerTemplate consumerTemplate;

    protected CamelContext createCamelContext() throws Exception{
        return context;
    }

    @Before
    public void setup() throws Exception {
        final RouteDefinition fileConsumer = context.getRouteDefinition("file-consumer");

        Assert.assertNotNull(fileConsumer);

        fileConsumer.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                interceptSendToEndpoint("direct:my-direct-component")
                        .skipSendToOriginalEndpoint()
                        .to("mock:direct-endpoint");
            }
        });
    }

    @Test
    public void test000() throws Exception {

        context.start();
        String fileEndpoint = "file:" + inputLocation;
        Exchange result = consumerTemplate.receive(fileEndpoint);
        Assert.assertEquals("test-data", result.getIn().getBody(String.class));
        context.stop();
    }
}