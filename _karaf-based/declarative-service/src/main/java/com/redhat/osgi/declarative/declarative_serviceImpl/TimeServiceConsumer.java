package com.redhat.osgi.declarative.declarative_serviceImpl;

import com.redhat.osgi.declarative.declarative_service.ITimeService;

public class TimeServiceConsumer {
	  private ITimeService service;

	  public void time() {
	    System.out.println(service.getCurrentTime());
	  }
	  
	  // Method will be used by DS to set the quote service
	  public synchronized void setTime(ITimeService service) {
	    System.out.println("Service was set. Thank you!");
	    this.service = service;
	    // I know I should not use the service here but just for demonstration
	    System.out.println(service.getCurrentTime());
	  }

	  // Method will be used by DS to unset the quote service
	  public synchronized void unsetTime(ITimeService service) {
	    System.out.println("Service was unset. Why did you do this to me?");
	    if (this.service == service) {
	      this.service = null;
	    }
	  }
	} 