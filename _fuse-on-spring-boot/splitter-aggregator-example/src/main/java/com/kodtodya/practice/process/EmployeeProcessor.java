package com.kodtodya.practice.process;

import com.kodtodya.practice.beans.Department;
import com.kodtodya.practice.beans.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeProcessor implements Processor {

    // generate the list of employees and sets it to Dept object
    @Override
    public void process(Exchange exchange) throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "Avadhut", "Finance"));
        employees.add(new Employee("2", "Kodtodya", "IT"));
        employees.add(new Employee("3", "Avi", "IT"));

        Department dept = new Department();
        dept.setEmployees(employees);

        exchange.getIn().setBody(dept);
    }
}
