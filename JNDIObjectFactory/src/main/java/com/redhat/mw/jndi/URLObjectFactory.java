package com.redhat.mw.jndi;

import java.net.URL;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

public class URLObjectFactory implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object object, Name name, Context context, Hashtable<?, ?> environment) throws Exception {
        System.out.println("Object: " + object);
        System.out.println("Name: " + name);
        System.out.println("Context: " + context);
        System.out.println("Environment: " + environment);

        return new URL("http://www.google.es");
    }

}
