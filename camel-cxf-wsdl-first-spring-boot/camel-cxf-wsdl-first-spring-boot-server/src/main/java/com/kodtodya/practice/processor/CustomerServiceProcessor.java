package com.kodtodya.practice.processor;

import cc.notsoclever.customerservice.Customer;
import cc.notsoclever.customerservice.CustomerService;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServiceProcessor implements Processor {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceProcessor.class);

    @Autowired
    CustomerService customerService;

    @Override
    public void process(Exchange exchange) throws Exception {
        Message inMessage = exchange.getIn();
        String operationName = inMessage.getHeader(CxfConstants.OPERATION_NAME, String.class);

        if ("getCustomersByName".equals(operationName)) {
            String name = inMessage.getBody(String.class);
            LOG.info("getCustomersByName called with: " + name);
            List<Customer> customers = customerService.getCustomersByName(name);
            exchange.getOut().setBody(new Object[] {customers});
        } else if ("updateCustomer".equals(operationName)) {
            LOG.info("updateCustomer called");
            Customer customer = inMessage.getBody(Customer.class);
            customer = customerService.updateCustomer(customer);
            exchange.getOut().setBody(customer);
        }
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
