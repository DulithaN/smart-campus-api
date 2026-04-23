package com.smartcampus.models;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.UUID;

public class SensorRoom {
    private String id;
    private String name;
    private String building;
    private int floor;
    private String roomType;
    private CopyOnWriteArrayList<String> sensorIds;
    
    public SensorRoom() {
        this.id = UUID.randomUUID().toString();
        this.sensorIds = new CopyOnWriteArrayList<>();
    }
    
    public SensorRoom(String name, String building, int floor, String roomType) {
        this();
        this.name = name;
        this.building = building;
        this.floor = floor;
        this.roomType = roomType;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }
    
    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }
    
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    
    public CopyOnWriteArrayList<String> getSensorIds() { return sensorIds; }
    public void setSensorIds(CopyOnWriteArrayList<String> sensorIds) { this.sensorIds = sensorIds; }
    
    public void addSensor(String sensorId) {
        this.sensorIds.add(sensorId);
    }
    
    public void removeSensor(String sensorId) {
        this.sensorIds.remove(sensorId);
    }
    
    public boolean hasSensors() {
        return !this.sensorIds.isEmpty();
    }
}