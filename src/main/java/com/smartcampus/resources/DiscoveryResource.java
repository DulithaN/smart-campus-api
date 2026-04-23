package com.smartcampus.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class DiscoveryResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscoveryInfo() {
        Map<String, Object> discovery = new HashMap<>();
        discovery.put("version", "1.0.0");
        discovery.put("apiName", "Smart Campus Sensor & Room Management API");
        discovery.put("contact", "admin@smartcampus.com");
        
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");
        discovery.put("resources", resources);
        
        Map<String, String> links = new HashMap<>();
        links.put("self", "/api/v1");
        links.put("rooms_collection", "/api/v1/rooms");
        links.put("sensors_collection", "/api/v1/sensors");
        discovery.put("_links", links);
        
        return Response.ok(discovery).build();
    }
}