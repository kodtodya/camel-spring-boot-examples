package com.citi.tts.apac.mongo.demo;
import org.springframework.stereotype.Component;


//POJO CLASS
@Component
public class Customer {

    public int EVENT_ID;

    public String first_name;
    
	public String last_name;
	
	public String formatted_message;

	public int getEVENT_ID() {
		return EVENT_ID;
	}

	public void setEVENT_ID(int eVENT_ID) {
		EVENT_ID = eVENT_ID;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFormatted_message() {
		return formatted_message;
	}

	public void setFormatted_message(String formatted_message) {
		this.formatted_message = formatted_message;
	}

	@Override
	public String toString() {
		return "Customer [EVENT_ID=" + EVENT_ID + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", formatted_message=" + formatted_message + "]";
	}

	public Customer(int eVENT_ID, String first_name, String last_name, String formatted_message) {
		super();
		EVENT_ID = eVENT_ID;
		this.first_name = first_name;
		this.last_name = last_name;
		this.formatted_message = formatted_message;
	}

	public Customer() {}
}