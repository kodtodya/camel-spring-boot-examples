package com.redhat.javadsl_osgi_sample;

/**
 * A bean which we use in the route
 */
public class MessageBean implements Message {

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message = "hard-coded message from MessageBean";

    @Override
	public String printMessage(String msg) {
    	if(msg!=null)message=msg;
		System.out.println(message);
		return message;
	}
    
}
