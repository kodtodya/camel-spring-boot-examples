package com.kodtodya.practice.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean("mongoDB")
    public MongoClient createMongoConnection(){
        return new MongoClient("localhost",27017);
    }
}
