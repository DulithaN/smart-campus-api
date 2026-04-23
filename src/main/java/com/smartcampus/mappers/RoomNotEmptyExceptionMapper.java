package com.smartcampus.mappers;

import com.smartcampus.exceptions.RoomNotEmptyException;
import com.smartcampus.models.ErrorResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {
    
    @Context
    private UriInfo uriInfo;
    
    @Override
    public Response toResponse(RoomNotEmptyException exception) {
        ErrorResponse error = new ErrorResponse(
            409,
            "Conflict",
            exception.getMessage(),
            uriInfo.getPath()
        );
        return Response.status(Response.Status.CONFLICT).entity(error).build();
    }
}