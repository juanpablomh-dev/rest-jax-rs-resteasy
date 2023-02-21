package org.jpmh.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Contact;
import io.swagger.models.Info;

@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

    public JaxRsActivator() {

        BeanConfig conf = new BeanConfig();

        conf.setTitle("REST API - JAX-RS - RESTEasy");
        conf.setDescription("REST API that exposes a service " +
                "that allows you to query, add, modify, and delete data about a person.");
        conf.setVersion("v1.0.0");
        conf.setHost("localhost:8080");
        conf.setResourcePackage("org.jpmh");
        conf.setBasePath("/rest-jax-rs-resteasy/rest");
        conf.setSchemes(new String[]{"HTTP", "HTTPS"});

        Contact contact = new Contact();
        contact.setEmail("juanpablo.mh5@gmail.com");
        contact.setName("Juan Pablo Muñoz");
        contact.setUrl("https://github.com/juanpablomh-dev");

        Info info = new Info();
        info.setDescription("API REST");
        info.setTitle("REST API - JAX-RS- RESTEasy");
        info.setVersion("v1.0.0");
        info.setContact(contact);
        conf.setInfo(info);
        conf.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(
                PersonResource.class,
                ApiListingResource.class,
                SwaggerSerializers.class));
    }
}