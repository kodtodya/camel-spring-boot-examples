package com.kodtodya.practice.routes;

import com.kodtodya.practice.processors.EmployeeProcessor;
import com.kodtodya.practice.strategy.DepartmentEmployeeStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SplitterRoute extends RouteBuilder {

    @Autowired
    private DepartmentEmployeeStrategy departmentEmployeeStrategy;

    @Autowired
    private EmployeeProcessor employeeProcessor;

    @Override
    public void configure() throws Exception {
        //in only
        from("timer:myTimer?period=1000&repeatCount=1")
                .process(employeeProcessor)
                // async communication
                .to("seda:processDept");

        // department object
        // splitter & aggregator
        from("seda:processDept")
                // body type = department.getEmployees()
                // here we will get a list of objects(Employees)
                // 10k records
                .split(simple("${body.getEmployees}"))
                // 1k records
                //you did certain processing
                .parallelProcessing()
                .aggregationStrategy(departmentEmployeeStrategy)
                .to("direct-vm:processEmployee").end();

        from("direct-vm:processEmployee")
                .choice()
                // body type = Employee
                // dept = Finance -> sending to processFinanceDept
                .when(simple("${body.getDepartment} == 'Finance'"))
                    .to("bean:employeeService?method=processFinanceDept")
                .when(simple("${body.getDepartment} == 'IT'"))
                    .to("bean:employeeService?method=processITDept");
    }
}