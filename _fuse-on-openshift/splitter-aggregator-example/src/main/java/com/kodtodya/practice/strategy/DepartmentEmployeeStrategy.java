package com.kodtodya.practice.strategy;

import com.kodtodya.practice.beans.Department;
import com.kodtodya.practice.beans.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentEmployeeStrategy implements AggregationStrategy {

    Logger logger = LoggerFactory.getLogger(DepartmentEmployeeStrategy.class);

    //  A->   Exchange(Message(data)) -> B
    public Exchange aggregate(Exchange oldEmployeeExchange, Exchange newEmployeeExchange) {
        
           if (oldEmployeeExchange == null) {
               Employee newEmployee= newEmployeeExchange.getIn().getBody(Employee.class);
               logger.info("Aggregate first employee: " + newEmployee);
               
               Department department = new Department();
               List<Employee> employees = new ArrayList<Employee>();
   
               employees.add(newEmployee);
               department.setEmployees(employees);
               department.setTotalSalary(newEmployee.getSalary());
               
               newEmployeeExchange.getIn().setBody(department);
               
               return newEmployeeExchange;
            }
           
            Department department = oldEmployeeExchange.getIn().getBody(Department.class);
            Employee newEmployee = newEmployeeExchange.getIn().getBody(Employee.class);

            logger.info("Aggregate old employees: " + department);
            logger.info("Aggregate new department: " + newEmployee);
            
            department.getEmployees().add(newEmployee);
           
            double totalSalary = department.getTotalSalary() + newEmployee.getSalary();
            department.setTotalSalary(totalSalary);

            return oldEmployeeExchange;
    }
}