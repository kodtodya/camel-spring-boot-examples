package com.kodtodya.practice.processors;

import com.kodtodya.practice.beans.Department;
import com.kodtodya.practice.beans.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("1", "Avadhut", "Finance"));
        employees.add(new Employee("2", "Kodtodya", "IT"));
        employees.add(new Employee("3", "Kodtodya1", "IT"));

        Department dept = new Department();
        dept.setEmployees(employees);

        Message message = exchange.getIn();
        message.setBody(dept);
    }
}