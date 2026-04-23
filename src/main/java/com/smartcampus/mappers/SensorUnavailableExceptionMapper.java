package com.smartcampus.mappers;

import com.smartcampus.exceptions.SensorUnavailableException;
import com.smartcampus.models.ErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {
    
    @Context
    private UriInfo uriInfo;
    
    @Override
    public Response toResponse(SensorUnavailableException exception) {
        ErrorResponse error = new ErrorResponse(
            403,
            "Forbidden",
            exception.getMessage(),
            uriInfo.getPath()
        );
        return Response.status(Response.Status.FORBIDDEN).entity(error).build();
    }
}