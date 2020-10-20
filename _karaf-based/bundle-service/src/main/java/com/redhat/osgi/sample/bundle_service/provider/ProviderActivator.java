package com.redhat.osgi.sample.bundle_service.provider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.redhat.osgi.sample.bundle_service.OSGIService;
import com.redhat.osgi.sample.bundle_service.impl.OSGIServiceImpl;

public class ProviderActivator implements BundleActivator {
    private ServiceRegistration registration;

    public void start(BundleContext bundleContext) throws Exception {
        registration = bundleContext.registerService(OSGIService.class.getName(),
                new OSGIServiceImpl(), null);
        System.out.println("Starting provider activator");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        registration.unregister();
        System.out.println("Stopped provider activator");
    }
}