package com.kodtodya.practice.process;

import com.kodtodya.practice.beans.Department;
import com.kodtodya.practice.beans.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentEmployeeStrategy implements AggregationStrategy {
    
    @Override
    public Exchange aggregate(Exchange oldEmployeeExchange, Exchange newEmployeeExchange) {
        
           if (oldEmployeeExchange == null) {
               
               Employee newEmployee= newEmployeeExchange.getIn().getBody(Employee.class);
               System.out.println("Aggregate first employee: " + newEmployee);
               
               Department department = new Department();
               List<Employee> employees = new ArrayList<>();
   
               employees.add(newEmployee);
               department.setEmployees(employees);
               department.setTotalSalary(newEmployee.getSalary());
               
               newEmployeeExchange.getIn().setBody(department);
               
                return newEmployeeExchange;
            }
           
            Department department = oldEmployeeExchange.getIn().getBody(Department.class);
            Employee newEmployee= newEmployeeExchange.getIn().getBody(Employee.class);
     
            System.out.println("Aggregate old employees: " + department);
            System.out.println("Aggregate new department: " + newEmployee);
            
            department.getEmployees().add(newEmployee);
           
            double totalSalary = department.getTotalSalary() + newEmployee.getSalary();
            department.setTotalSalary(totalSalary);

            return oldEmployeeExchange;
    }

}