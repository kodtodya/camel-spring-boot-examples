package com.kodtodya.practice.services;

import com.kodtodya.practice.beans.Customer;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TransformationService {

    public void transformMessage(Exchange exchange){
        Message in = exchange.getIn();
        Customer customer = in.getBody(Customer.class);

        StringBuilder response = new StringBuilder();
        response.append("id=" + customer.getId())
                .append("|name=" + customer.getName())
                .append("|address=" + customer.getAddress())
                .append("|payment-method=" + customer.getPaymentMethod());

        in.setBody(response);
    }
}