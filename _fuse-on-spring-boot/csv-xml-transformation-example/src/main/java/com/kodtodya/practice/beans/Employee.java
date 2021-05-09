package com.kodtodya.practice.beans;

import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// avoid boiler plate code using lombok - check dependency in pom.xml
@Data

// CSV formatting related annotations
@CsvRecord( separator = "," )

// xml formatting related annotations
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

    // @XmlElement annotation refers to element values of xml and
    // @DataField annotation refers to CSV data field values of csv record

    @XmlElement(name = "EmployeeID")
    @DataField(name = "Employee Id", pos = 1)
    public String id;

    @XmlElement(name = "Name")
    @DataField(name = "Name", pos = 2)
    public String name;

    @XmlElement(name = "Salary")
    @DataField(name = "Salary", pos = 3)
    public int salary;
}
