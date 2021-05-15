package com.kodtodya.practice.routes;

import com.kodtodya.practice.process.DepartmentEmployeeStrategy;
import com.kodtodya.practice.process.EmployeeProcessor;
import com.kodtodya.practice.process.ProcessEmployees;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SplitterRoute extends RouteBuilder {

    @Autowired
    private EmployeeProcessor processor;

    @Override
    public void configure() throws Exception {

        // timer route to generate the event after each 1000ms for only once
        from("timer:myTimer?period=1000&repeatCount=1").id("timer-component")
                // creates List<Employee> in below processor and sets it to Dept object
                .process(processor).id("employee-list-generator")
                // sends the Dept object to direct component
                .to("direct:processDept").id("direct-process-dept-producer");

        // direct consumer consumes Dept object
        from("direct:processDept").id("direct-process-dept-consumer")
                // split the received list based on getEmployees() method of Dept
                // we are applying getEmployees() method on body because body contains Dept object
                .split(body().method("getEmployees"), new DepartmentEmployeeStrategy())
                // send each record separately for processing
                .to("direct:processEmployee").end();

        // direct component for processing employee records
        from("direct:processEmployee").choice()
                // if(employee.Department == 'Finance')
                .when(body().method("getDepartment").isEqualTo("Finance"))
                // then process it to employeeService.processFinanceDept()
                .to("bean:employeeService?method=processFinanceDept")

                // if(employee.Department == 'IT')
                .when(body().method("getDepartment").isEqualTo("IT"))
                // then process it to employeeService.processITDept()
                .to("bean:employeeService?method=processITDept");
    }

}
