package com.kodtodya.cxf.wsdlfirst.client.routes;

import cc.notsoclever.customerservice.Customer;
import cc.notsoclever.customerservice.NoSuchCustomerException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.message.MessageContentsList;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CxfClientRoutes extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(CxfClientRoutes.class);

    @Override
    public void configure() throws Exception {

        LOG.info("Starting client routes");

        // Fire off all the tests.
        from("timer://NotFoundTest?repeatCount=1")
                .multicast()
                .to("direct:getCustomersTest", "direct:noSuchCustomerTest", "direct:updateCustomerTest");

        // Test noSuchCustomerException
        from("direct:noSuchCustomerTest")
                .onException(NoSuchCustomerException.class)
                .log("SUCCESS: NotFoundTest - NoSuchCustomerException detected.")
                .handled(true)
                .end()
                .setHeader(CxfConstants.OPERATION_NAMESPACE, simple("http://customerservice.notsoclever.cc/"))
                .setHeader(CxfConstants.OPERATION_NAME, simple("getCustomersByName"))
                .setBody(simple("Walker"))
                .to("cxf:bean:customerServiceEndpoint");

        // Test getCustomersByName
        from("direct:getCustomersTest")
                .setHeader(CxfConstants.OPERATION_NAMESPACE, simple("http://customerservice.notsoclever.cc/"))
                .setHeader(CxfConstants.OPERATION_NAME, simple("getCustomersByName"))
                .setBody(simple("Johns"))
                .to("cxf:bean:customerServiceEndpoint")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        MessageContentsList contents = exchange.getIn().getBody(MessageContentsList.class);

                        @SuppressWarnings("unchecked")
                        List<Customer> customers = (List<Customer>) contents.get(0);
                        Assert.assertEquals(2, customers.size());
                        Set<String> customerNames = new HashSet<String>();
                        for (Customer customer : customers) {
                            customerNames.add(customer.getName());
                        }
                        Assert.assertTrue("expected customer name not found", customerNames.contains("Johns, Mary"));
                        Assert.assertTrue("expected customer name not found", customerNames.contains("Johns, Marvin"));
                        LOG.info("SUCCESS: getCustomersByName");
                    }
                });

        // Test updateCustomer
        // 1 - Get a customer and set a new value for number of orders
        from("direct:updateCustomerTest")
                .setHeader(CxfConstants.OPERATION_NAMESPACE, simple("http://customerservice.notsoclever.cc/"))
                .setHeader(CxfConstants.OPERATION_NAME, simple("getCustomersByName"))
                .setBody(simple("Jones"))
                .to("cxf:bean:customerServiceEndpoint")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        MessageContentsList contents = exchange.getIn().getBody(MessageContentsList.class);
                        List customers = (List) contents.get(0);
                        Customer customer = (Customer) customers.get(0);
                        customer.setNumOrders(99);
                        exchange.getIn().setBody(customer);
                    }
                })
                .to("direct:sendUpdate");

        // 2 - Send the updated customer to updateCustomer
        from("direct:sendUpdate")
                .setHeader(CxfConstants.OPERATION_NAMESPACE, simple("http://customerservice.notsoclever.cc/"))
                .setHeader(CxfConstants.OPERATION_NAME, simple("updateCustomer"))
                .to("cxf:bean:customerServiceEndpoint")
                .to("direct:confirmUpdate");

        // 3 - Retrieve the results of the update and confirm that the values are set
        from("direct:confirmUpdate")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Customer customer = exchange.getIn().getBody(Customer.class);
                        Assert.assertEquals(99, (int) customer.getNumOrders());
                        LOG.info("SUCCESS: updateCustomer");
                    }
                });
    }
}
