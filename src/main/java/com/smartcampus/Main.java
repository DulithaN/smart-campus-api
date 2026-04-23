package com.smartcampus;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        
        ServletHolder holder = new ServletHolder(new ServletContainer());
        holder.setInitParameter("jersey.config.server.provider.packages", "com.smartcampus");
        holder.setInitParameter("jersey.config.server.provider.classnames", 
            "com.smartcampus.resources.DiscoveryResource;" +
            "com.smartcampus.resources.RoomResource;" +
            "com.smartcampus.resources.SensorResource;" +
            "com.smartcampus.resources.SensorReadingResource");
        
        context.addServlet(holder, "/*");
        
        server.start();
        System.out.println("Server started at http://localhost:8080/api/v1");
        server.join();
    }
}