package com.smartcampus.api.exception;

public class RoomNotEmptyException extends RuntimeException {
    private String roomId;
    private int sensorCount;
    
    public RoomNotEmptyException(String roomId, int sensorCount) {
        super("Room " + roomId + " cannot be deleted as it contains " + sensorCount + " active sensor(s)");
        this.roomId = roomId;
        this.sensorCount = sensorCount;
    }
    
    public String getRoomId() { return roomId; }
    public int getSensorCount() { return sensorCount; }
}
