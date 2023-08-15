package com.redhat.camel.blueprint.osgi.interfaces;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public interface AMQJNDIResolver {

	String JNDI_FACTORY = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
	
	public InitialContext getInitialContext(String url) throws NamingException;
}
