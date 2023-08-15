package com.redhat.camel.blueprint.exception.handling;

import org.apache.camel.main.Main;

public class ExceptionHandler{

    public static void main(String...args) throws Exception{

        Main main = new Main();

        main.enableHangupSupport();

        main.addRouteBuilder(new MyRouteBuilder());

        main.run();
    }
}
