package com.smartcampus.models;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Sensor {
    private String id;
    private String name;
    private String type;
    private String roomId;
    private String unit;
    private String status; // "ACTIVE", "MAINTENANCE", "OFFLINE"
    private double currentValue;
    private CopyOnWriteArrayList<SensorReading> readings;
    
    public Sensor() {
        this.id = UUID.randomUUID().toString();
        this.readings = new CopyOnWriteArrayList<>();
        this.status = "ACTIVE";
        this.currentValue = 0.0;
    }
    
    public Sensor(String name, String type, String roomId, String unit) {
        this();
        this.name = name;
        this.type = type;
        this.roomId = roomId;
        this.unit = unit;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }
    
    public CopyOnWriteArrayList<SensorReading> getReadings() { return readings; }
    public void setReadings(CopyOnWriteArrayList<SensorReading> readings) { this.readings = readings; }
    
    public void addReading(SensorReading reading) {
        this.readings.add(reading);
        this.currentValue = reading.getValue();
    }
}