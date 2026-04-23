package com.smartcampus.resources;

import com.smartcampus.DataStore;
import com.smartcampus.exceptions.ResourceNotFoundException;
import com.smartcampus.models.Sensor;
import com.smartcampus.models.SensorRoom;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {
    
    private DataStore dataStore = DataStore.getInstance();
    
    @GET
    public Response getAllSensors(@QueryParam("type") String type) {
        List<Sensor> sensors = new ArrayList<>(dataStore.getSensors().values());
        
        if (type != null && !type.trim().isEmpty()) {
            sensors = sensors.stream()
                .filter(s -> s.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
        }
        
        return Response.ok(sensors).build();
    }
    
    @POST
    public Response createSensor(Sensor sensor, @Context UriInfo uriInfo) {
        // Validate room exists
        SensorRoom room = dataStore.getRooms().get(sensor.getRoomId());
        if (room == null) {
            throw new ResourceNotFoundException("Room with ID " + sensor.getRoomId() + " does not exist");
        }
        
        dataStore.getSensors().put(sensor.getId(), sensor);
        room.addSensor(sensor.getId());
        
        URI location = uriInfo.getAbsolutePathBuilder().path(sensor.getId()).build();
        return Response.created(location).entity(sensor).build();
    }
    
    @GET
    @Path("/{sensorId}")
    public Response getSensorById(@PathParam("sensorId") String sensorId) {
        Sensor sensor = dataStore.getSensors().get(sensorId);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(sensor).build();
    }
    
    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        Sensor sensor = dataStore.getSensors().get(sensorId);
        if (sensor == null) {
            throw new ResourceNotFoundException("Sensor with ID " + sensorId + " not found");
        }
        return new SensorReadingResource(sensor);
    }
}