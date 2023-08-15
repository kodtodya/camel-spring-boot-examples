package com.redhat.bean_camel_sample;


public class MessageBean implements Message {

	private String message = "hard coded message in message bean";
	@Override
	public String printMessage(String msg) {
		if(null!=msg){
			message = msg;
		}
		System.out.println(message);
		return message;
	}

}
