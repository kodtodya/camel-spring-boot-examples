package com.kodtodya.practice.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

//@JsonRootName(value = "customer")
public class Customer {

    //@JsonProperty("id")
    private String id;

    //@JsonProperty("name")
    private String name;

    //@JsonProperty("address")
    private String address;

    //@JsonProperty("paymentMethod")
    private String paymentMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
