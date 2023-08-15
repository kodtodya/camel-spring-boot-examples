package com.redhat.camel_java_camelProcessor;


public class MessageBean 
{
	private String msg = "initial hard-coded message";
	
    public void printMsg()
    {
        System.out.println(msg);
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
