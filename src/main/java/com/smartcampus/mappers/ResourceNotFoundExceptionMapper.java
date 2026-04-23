package com.smartcampus.mappers;

import com.smartcampus.exceptions.ResourceNotFoundException;
import com.smartcampus.models.ErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    
    @Context
    private UriInfo uriInfo;
    
    @Override
    public Response toResponse(ResourceNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
            422,
            "Unprocessable Entity",
            exception.getMessage(),
            uriInfo.getPath()
        );
        return Response.status(422).entity(error).build();
    }
}