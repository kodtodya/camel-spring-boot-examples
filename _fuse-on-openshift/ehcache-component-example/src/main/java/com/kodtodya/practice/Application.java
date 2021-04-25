package com.kodtodya.practice;

import com.kodtodya.practice.routes.EhcacheRoute;
import org.apache.camel.Main;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {

    @Autowired
    private EhcacheRoute ehcacheRoute;

    @Autowired
    private CacheManager cacheManager;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        Main main = new Main();
        main.bind("cacheManager", cacheManager);
        main.addRouteBuilder(ehcacheRoute);
        main.run();
    }
}