package com.redhat.osgi.test.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.redhat.osgi.sample.bundle_service.OSGIService;

public class OSGIActivator implements BundleActivator {
    private OSGIConsumer consumer;

    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference reference = bundleContext.getServiceReference(OSGIService.class.getName());

        consumer = new OSGIConsumer((OSGIService) bundleContext.getService(reference));
        consumer.startTimer();
        System.out.println("starting OSGI activator");
    }

   public void stop(BundleContext bundleContext) throws Exception {
        consumer.stopTimer();
        System.out.println("stopped OSGI activator");
    }
}