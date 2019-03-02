package com.kodtodya.practice;

import org.apache.camel.Main;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.ehcache.EhcacheConstants;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.xml.XmlConfiguration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        URL url = Application.class.getResource("/ehcache.xml");

        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(url));
        cacheManager.init();

        Main main = new Main();
        main.bind("cacheManager", cacheManager);

        main.addRouteBuilder(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("timer:foo?period=5s&repeatCount=1")
                        .log("Timer triggered.. we will insert data into ehcache..")
                        .process(exchange ->{
                            Map<String, String> inputMap = new HashMap<>();
                            inputMap.put("id", "kodtodya");
                            inputMap.put("company", "kodtodya-talks");
                            inputMap.put("city", "Pune");
                            exchange.getIn().setBody(inputMap);
                            exchange.getIn().setHeader("CamelEhcacheAction", EhcacheConstants.ACTION_PUT_ALL);
                        })
                        .to("ehcache://testCache?cacheManager=#cacheManager&keyType=java.lang.String&valueType=java.lang.String")
                        .log("Data sent to EhCache.. now trying to read it..")
                        .process(exchange -> {
                            //here I want to access already created testCache component but it is creating new one.
                            Cache<String, String> cache = cacheManager.getCache("testCache", String.class, String.class);
                            System.out.println("Cache Element:"+cache.get("id"));
                            System.out.println("Exchange Message:"+exchange.getIn().getBody());

                        });
            }
        });

        main.run();
    }

}