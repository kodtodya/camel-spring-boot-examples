package com.redhat.camel.blueprint.camProcess;


/**
 * A bean which we use in the route
 */
public class MessageBean {

    private String msg = "initial message";

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
