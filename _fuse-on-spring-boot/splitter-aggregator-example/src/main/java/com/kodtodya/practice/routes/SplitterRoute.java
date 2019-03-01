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

        from("timer:myTimer?period=1000&repeatCount=1")
                .process(processor)
                .to("direct:processDept");



        from("direct:processDept")
                .split(body().method("getEmployees"),
                        new DepartmentEmployeeStrategy())
                .to("direct:processEmployee").end();

        from("direct:processEmployee").choice()
                .when(body().method("getDepartment").isEqualTo("Finance"))
                .to("bean:employeeService?method=processFinanceDept")
                .when(body().method("getDepartment").isEqualTo("IT"))
                .to("bean:employeeService?method=processITDept");
    }

}
