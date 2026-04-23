package com.smartcampus.resources;

import com.smartcampus.DataStore;
import com.smartcampus.exceptions.RoomNotEmptyException;
import com.smartcampus.models.ErrorResponse;
import com.smartcampus.models.SensorRoom;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/api/v1/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    private DataStore dataStore = DataStore.getInstance();

    @GET
    public Response getAllRooms() {
        List<SensorRoom> rooms = new ArrayList<>(dataStore.getRooms().values());
        return Response.ok(rooms).build();
    }

    @POST
    public Response createRoom(SensorRoom room, @Context UriInfo uriInfo) {
        dataStore.getRooms().put(room.getId(), room);
        URI location = uriInfo.getAbsolutePathBuilder().path(room.getId()).build();
        return Response.created(location).entity(room).build();
    }

    @GET
    @Path("/{roomId}")
    public Response getRoomById(@PathParam("roomId") String roomId) {
        SensorRoom room = dataStore.getRooms().get(roomId);
        if (room == null) {
            ErrorResponse error = new ErrorResponse(
                    404,
                    "Not Found",
                    "Room not found: " + roomId,
                    "/api/v1/rooms/" + roomId
            );
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
        return Response.ok(room).build();
    }

    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        SensorRoom room = dataStore.getRooms().get(roomId);
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (room.hasSensors()) {
            throw new RoomNotEmptyException("Cannot delete room with active sensors. Remove all sensors first.");
        }

        dataStore.getRooms().remove(roomId);
        return Response.noContent().build();
    }
}