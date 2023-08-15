package com.mycompany.camel.spring.dm;

import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.Uri;
import org.apache.camel.component.properties.DefaultPropertiesParser;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.component.properties.PropertiesParser;
import org.apache.camel.management.event.CamelContextStartedEvent;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.deltaspike.core.api.config.ConfigResolver;

public class RouteHandler {

    @ContextName("hello")
    static class HelloRoute extends RouteBuilder {

        @Override
        public void configure() {
            // Property placeholders in endpoint URIs are resolved
            // based on configuration properties
            from("{{destination}}").log("${body} from CamelContext (${camelContext.name})");

        }
    }

    void hello(@Observes CamelContextStartedEvent event,
               // Configuration properties can be injected with @ConfigProperty
               @ConfigProperty(name = "message") String message,
               // Property placeholders in @Uri qualifier are also resolved
               @Uri("{{destination}}") ProducerTemplate producer) {
        producer.sendBody(message);
    }

    @Produces
    @ApplicationScoped
    @Named("properties")
    // "properties" component bean that Camel uses to lookup properties
    PropertiesComponent properties(PropertiesParser parser) {
        PropertiesComponent component = new PropertiesComponent();
        // Use DeltaSpike as configuration source for Camel CDI
        component.setPropertiesParser(parser);
        return component;
    }

    // PropertiesParser bean that uses DeltaSpike to resolve properties
    static class DeltaSpikeParser extends DefaultPropertiesParser {

        @Override
        public String parseProperty(String key, String value, Properties properties) {
            return ConfigResolver.getPropertyValue(key);
        }
    }
}