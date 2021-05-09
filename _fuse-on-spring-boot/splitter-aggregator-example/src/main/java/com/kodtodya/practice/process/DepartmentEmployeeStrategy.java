package com.kodtodya.practice.process;

import com.kodtodya.practice.beans.Department;
import com.kodtodya.practice.beans.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentEmployeeStrategy implements AggregationStrategy {
    
    @Override
    public Exchange aggregate(Exchange oldEmployeeExchange, Exchange newEmployeeExchange) {
        // Iterations  | Old Exchange data                  | New Exchange Data | return type |
        // Iteration-0 | NULL                               | Employee-1        | Department  |
        // Iteration-1 | Employee-1                         | Employee-2        | Department  |
        // Iteration-2 | Employee-1,Employee-2              | Employee-3        | Department  |
        // Iteration-3 | Employee-1,Employee-2,Employee-3   | Employee-4        | Department  |
        //.... and so on..

        // very first time, oldEmployeeExchange will be null
        // hence below if condition should run
        // and setting up oldEmployeeExchange = newEmployeeExchange breaks the camel flow
        // because camel does not copy references of newEmployeeExchange
           if (oldEmployeeExchange == null) {
               // newEmployeeExchange's  will always provide us New Employee()
               Message inMessage = newEmployeeExchange.getIn();

               // extracting employee object from IN Message
               Employee newEmployee= inMessage.getBody(Employee.class);
               System.out.println("Aggregate first employee: " + newEmployee);

               // creation of Dept object
               Department department = new Department();
               List<Employee> employees = new ArrayList<>();
   
               employees.add(newEmployee);
               // set employee object to Dept
               department.setEmployees(employees);
               department.setTotalSalary(newEmployee.getSalary());
               // set dept object in exchange body
               newEmployeeExchange.getIn().setBody(department);
               
                return newEmployeeExchange;
            }

           // get the department object with old data
            Department department = oldEmployeeExchange.getIn().getBody(Department.class);
           // get the new Employee object from newly received exchange
            Employee newEmployee= newEmployeeExchange.getIn().getBody(Employee.class);

            // print here
            System.out.println("\nAggregate old employees: " + department);
            System.out.println("Aggregate new department: " + newEmployee);

            // add the newly received employee object to department
            department.getEmployees().add(newEmployee);

            // print updated department data
            System.out.println("\nAggregate Department object with employees: " + department);

            // calculate aggregate salary of department
            double totalSalary = department.getTotalSalary() + newEmployee.getSalary();
            System.out.println("Aggregate Salary of Department with all employees: " + totalSalary);
            department.setTotalSalary(totalSalary);

            return oldEmployeeExchange;
    }

}