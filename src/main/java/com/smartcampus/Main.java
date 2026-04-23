package com.smartcampus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Manually register all resources
        ResourceConfig config = new ResourceConfig();
        config.register(com.smartcampus.resources.DiscoveryResource.class);
        config.register(com.smartcampus.resources.RoomResource.class);
        config.register(com.smartcampus.resources.SensorResource.class);
        config.register(com.smartcampus.resources.SensorReadingResource.class);
        config.register(com.smartcampus.mappers.RoomNotEmptyExceptionMapper.class);
        config.register(com.smartcampus.mappers.ResourceNotFoundExceptionMapper.class);
        config.register(com.smartcampus.mappers.SensorUnavailableExceptionMapper.class);
        config.register(com.smartcampus.mappers.GlobalExceptionMapper.class);
        config.register(com.smartcampus.filters.LoggingFilter.class);

        ServletHolder holder = new ServletHolder(new ServletContainer(config));
        context.addServlet(holder, "/*");

        server.start();
        System.out.println("========================================");
        System.out.println("Smart Campus API Server Started!");
        System.out.println("Base URL: http://localhost:8080/api/v1");
        System.out.println("Discovery: http://localhost:8080/api/v1");
        System.out.println("========================================");
        server.join();
    }
}