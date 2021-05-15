package com.kodtodya.practice.process;

import com.kodtodya.practice.beans.Employee;
import org.springframework.stereotype.Component;

@Component("employeeService")
public class ProcessEmployees {

	// process IT dept employee record
	public Employee processITDept(Employee employee)
			throws InterruptedException {

		System.out.println("\n\nhandling employee department:" + employee);
		employee.setSalary(10000);

		System.out.println("IT dept employee processed");

		return employee;
	}

	// process Finance dept employee record
	public Employee processFinanceDept(Employee employee)
			throws InterruptedException {

		System.out.println("\n\nhandling employee department:" + employee);
		employee.setSalary(5000);

		System.out.println("Finance dept employee processed");

		return employee;
	}
}