package com.smartcampus.api.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {
    @Override
    public Response toResponse(RoomNotEmptyException exception) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 409);
        error.put("error", "Conflict");
        error.put("message", exception.getMessage());
        error.put("roomId", exception.getRoomId());
        error.put("sensorCount", exception.getSensorCount());
        error.put("timestamp", System.currentTimeMillis());
        return Response.status(Response.Status.CONFLICT).entity(error).build();
    }
}
