package com.redhat.camel.blueprint.parentBundle;


/**
 * A bean which we use in the route
 */
public class MessageBean{

    private String msg = "Hello World";

    public String printMsg() {
        return msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
