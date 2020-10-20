package com.redhat.osgi.declarative.declarative_serviceImpl;

import com.redhat.osgi.declarative.declarative_service.ITimeService;

public class TimeServiceImpl implements ITimeService {

	public Long getCurrentTime() {
		System.out.println("TimeServiceImpl.  Received call to getCurrentTime()");
		return new Long(System.currentTimeMillis());
	}
}