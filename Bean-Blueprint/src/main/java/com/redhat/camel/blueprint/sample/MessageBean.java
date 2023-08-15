package com.redhat.camel.blueprint.sample;

/**
 * A bean which we use in the route
 */
public class MessageBean implements Hello {

    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String msg = "Hello World";

    public String printMsg() {
        System.out.println("existing message: "+msg);
        msg ="changed messsage in printmsg() method";
        return msg;
    }
}
