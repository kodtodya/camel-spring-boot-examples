package com.redhat.camel.blueprint.osgi.service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.redhat.camel.blueprint.osgi.interfaces.AMQJNDIResolver;

public class AMQJNDIResolverImpl implements AMQJNDIResolver {

	public InitialContext getInitialContext(String url) throws NamingException {
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);
        return new InitialContext(env);
	}

}
