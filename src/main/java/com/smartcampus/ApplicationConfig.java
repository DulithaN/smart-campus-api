package com.smartcampus;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        // Register resources
        resources.add(com.smartcampus.resources.DiscoveryResource.class);
        resources.add(com.smartcampus.resources.RoomResource.class);
        resources.add(com.smartcampus.resources.SensorResource.class);
        resources.add(com.smartcampus.resources.SensorReadingResource.class);
        // Register exception mappers
        resources.add(com.smartcampus.mappers.RoomNotEmptyExceptionMapper.class);
        resources.add(com.smartcampus.mappers.ResourceNotFoundExceptionMapper.class);
        resources.add(com.smartcampus.mappers.SensorUnavailableExceptionMapper.class);
        resources.add(com.smartcampus.mappers.GlobalExceptionMapper.class);
        // Register filters
        resources.add(com.smartcampus.filters.LoggingFilter.class);
        return resources;
    }
}