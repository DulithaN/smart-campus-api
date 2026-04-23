package com.smartcampus;

import com.smartcampus.models.Sensor;
import com.smartcampus.models.SensorRoom;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    private static DataStore instance;
    private final ConcurrentHashMap<String, SensorRoom> rooms;
    private final ConcurrentHashMap<String, Sensor> sensors;
    
    private DataStore() {
        this.rooms = new ConcurrentHashMap<>();
        this.sensors = new ConcurrentHashMap<>();
        initializeSampleData();
    }
    
    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }
    
    private void initializeSampleData() {
        SensorRoom room1 = new SensorRoom("Lab 101", "Engineering Building", 1, "Laboratory");
        SensorRoom room2 = new SensorRoom("Lecture Hall A", "Main Building", 2, "Lecture Hall");
        rooms.put(room1.getId(), room1);
        rooms.put(room2.getId(), room2);
    }
    
    public ConcurrentHashMap<String, SensorRoom> getRooms() { return rooms; }
    public ConcurrentHashMap<String, Sensor> getSensors() { return sensors; }
}