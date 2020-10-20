package com.redhat.osgi.test.consumer;

import javax.swing.Timer;

import com.redhat.osgi.sample.bundle_service.OSGIService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OSGIConsumer implements ActionListener {
    private final OSGIService service;
    private final Timer timer;

    public OSGIConsumer(OSGIService service) {
        super();

        this.service = service;

        timer = new Timer(1000, this);
    }

    public void startTimer(){
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void actionPerformed(ActionEvent e) {
        service.helperMethod();
    }
}