package com.kodtodya.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.kodtodya.practice")
public class SpringBootKafkaExample 
{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaExample.class, args);
		}
}
