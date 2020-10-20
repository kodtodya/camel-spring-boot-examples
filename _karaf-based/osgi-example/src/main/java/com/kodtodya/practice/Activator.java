package com.kodtodya.practice;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {
    private BundleContext context;

    private Logger logger = Logger.getLogger(Activator.class);

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("Starting Bundle: Hello World...");
        System.out.println("Starting Bundle: Hello World...");
        this.context = context;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("Stopping Bundle: Goodbye Cruel World...");
        System.out.println("Stopping Bundle: Goodbye Cruel World...");
        this.context = null;
    }
}