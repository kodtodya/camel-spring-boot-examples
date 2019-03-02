package com.kodtodya.practice.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class SQLProcessor implements Processor {

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public void process(Exchange exchange) throws Exception {

        String query = "INSERT INTO ORGANIZATIONS(ID, NAME, LOCATION) VALUES ("+this.getRandomNumber()+", '"+ this.getRandomString()+"', '"+this.getRandomString()+"')";
        exchange.getIn().setBody(query);
    }

    private String getRandomString() {
        StringBuffer randStr = new StringBuffer(8);
        SecureRandom secureRandom = new SecureRandom();
        for( int i = 0; i < 8; i++ )
            randStr.append( CHAR_LIST.charAt( secureRandom.nextInt(CHAR_LIST.length())));
        return randStr.toString();

    }

    private int getRandomNumber() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
