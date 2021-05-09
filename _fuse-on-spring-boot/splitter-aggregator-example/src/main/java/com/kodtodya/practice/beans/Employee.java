package com.kodtodya.practice.beans;

// Employee POJO class
public class Employee {

    public Employee(String empId, String name, String type) {
        this.empId = empId;
        this.name = name;
        this.department = type;
    }

    private String empId;

    private String name;
    
    private String department;

    private double salary;

    public String getId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Employee [empId=");
        builder.append(empId);
        builder.append(", name=");
        builder.append(name);
        builder.append(", department=");
        builder.append(department);
        builder.append(", salary=");
        builder.append(salary);
        builder.append("]");
        return builder.toString();
    }
    
}