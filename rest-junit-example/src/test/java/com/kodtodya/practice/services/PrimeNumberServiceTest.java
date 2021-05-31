package com.kodtodya.practice.services;

import com.kodtodya.practice.Application;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("unittest")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PrimeNumberServiceTest {

    @Autowired
    private PrimeNumberService primeNumberService;

    @Test
    public void test000() {
        String response = primeNumberService.isPrime(11);
        Assert.assertEquals("11 is Prime", response);
    }
}
