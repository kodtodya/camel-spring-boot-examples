package com.kodtodya.practice.beans;

import java.util.List;

public class Department {
    
    private List<Employee> employees;
    
    private double totalSalary;
        

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }    

     public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Department");
        builder.append(", Employees=");
        builder.append(employees);
        builder.append(", totalSalary=");
        builder.append(totalSalary);
        builder.append("]");
        return builder.toString();
    }

}