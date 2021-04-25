package com.kodtodya.practice.routes;

import com.kodtodya.practice.Application;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ehcache.EhcacheConstants;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class EhcacheRoute extends RouteBuilder {

    public CacheManager cacheManager;

    @PropertyInject("{{spring.cache.jcache.config}}")
    private String cacheConfigFile;

    @Override
    public void configure() throws Exception {

        from("timer:foo?period=5s&repeatCount=1")
                .log("------- Timer triggered.. we will insert data into ehcache. ------- ")
                .process(exchange ->{
                    Map<String, String> inputMap = new HashMap<>();
                    inputMap.put("id", "kodtodya");
                    inputMap.put("company", "kodtodya-talks");
                    inputMap.put("city", "Pune");
                    exchange.getIn().setBody(inputMap);
                    exchange.getIn().setHeader("CamelEhcacheAction", EhcacheConstants.ACTION_PUT_ALL);
                })
                .to("ehcache://testCache?cacheManager=#cacheManager&keyType=java.lang.String&valueType=java.lang.String")
                .log("------- Data sent to EhCache.. now trying to read it ------- ")
                .process(exchange -> {
                    //here I want to access already created testCache component but it is creating new one.
                    Cache<String, String> cache = cacheManager.getCache("testCache", String.class, String.class);
                    exchange.setProperty("cache-element", cache.get("id"));

                })
                .log("Cache Element: ${exchangeProperty.cache-element}")
                .log("Exchange Message: ${body}")
                .log("------------------------------------------------------");
    }


    @Bean(name = "cacheManager")
    public CacheManager getCacheManager() throws MalformedURLException {
        URL url = Application.class.getResource(cacheConfigFile);
        cacheManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(url));
        cacheManager.init();
        return cacheManager;
    }
}
