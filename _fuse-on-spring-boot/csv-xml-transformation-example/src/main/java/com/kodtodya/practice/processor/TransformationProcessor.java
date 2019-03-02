package com.kodtodya.practice.processor;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class TransformationProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String myString = exchange.getIn().getBody(String.class);
        String[] lineSeparator = myString.split(System.getProperty("line.separator"));
        StringBuffer sb = new StringBuffer();
        sb.append("<Employees>");
        for (String lineData : lineSeparator)
        {
            String [] commaSeparator=lineData.split(",");
            sb.append("<Employee>");
            sb.append("<EmployeeID>"+commaSeparator[0].toString()+"</EmployeeID>");
            sb.append("<Name>"+commaSeparator[1].toString()+"</Name>");
            sb.append("<Salary>"+commaSeparator[2].toString()+"</Salary>");
            sb.append("</Employee>");
        }
        sb.append("</Employees>");
        System.out.println("Transformation completed...");
        exchange.getIn().setBody(sb.toString());
    }
}