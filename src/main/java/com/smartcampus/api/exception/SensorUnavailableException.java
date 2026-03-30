package com.smartcampus.api.exception;

public class SensorUnavailableException extends RuntimeException {
    private String sensorId;
    private String status;
    
    public SensorUnavailableException(String sensorId, String status) {
        super("Sensor " + sensorId + " is currently in " + status + " state and cannot accept readings");
        this.sensorId = sensorId;
        this.status = status;
    }
    
    public String getSensorId() { return sensorId; }
    public String getStatus() { return status; }
}
