package com.smartcampus.api.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {
    @Override
    public Response toResponse(SensorUnavailableException exception) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 403);
        error.put("error", "Forbidden");
        error.put("message", exception.getMessage());
        error.put("sensorId", exception.getSensorId());
        error.put("sensorStatus", exception.getStatus());
        error.put("timestamp", System.currentTimeMillis());
        return Response.status(Response.Status.FORBIDDEN).entity(error).build();
    }
}
