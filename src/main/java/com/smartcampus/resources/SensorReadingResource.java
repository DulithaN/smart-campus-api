package com.smartcampus.resources;

import com.smartcampus.exceptions.SensorUnavailableException;
import com.smartcampus.models.Sensor;
import com.smartcampus.models.SensorReading;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {
    
    private final Sensor sensor;
    
    public SensorReadingResource(Sensor sensor) {
        this.sensor = sensor;
    }
    
    @GET
    public Response getReadingHistory() {
        List<SensorReading> readings = sensor.getReadings();
        return Response.ok(readings).build();
    }
    
    @POST
    public Response addReading(SensorReading reading) {
        // Check if sensor is in maintenance mode
        if ("MAINTENANCE".equals(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor is in MAINTENANCE mode and cannot accept new readings");
        }
        
        sensor.addReading(reading);
        return Response.status(Response.Status.CREATED).entity(reading).build();
    }
}