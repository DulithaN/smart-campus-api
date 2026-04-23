# Smart Campus Sensor & Room Management API

## Course Information
- **Module:** Client-Server Architectures (5COSC022W)
- **Module Leader:** Hamed Hamzeh
- **Year:** 2025/26
- **Weighting:** 60% of final grade

---

## Overview

This RESTful API manages a university's "Smart Campus" infrastructure, handling rooms, sensors, and sensor readings. Built with JAX-RS (Jersey) following REST principles and HATEOAS.

**Key Features:**
- RESTful API design with proper HTTP methods
- In-memory data storage using ConcurrentHashMap
- Sub-resource locator pattern for nested resources
- Comprehensive error handling with custom exception mappers
- Request/response logging filters
- HATEOAS discovery endpoint

---

## Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| JAX-RS | 3.1.0 | RESTful API Framework |
| Jersey | 3.1.3 | JAX-RS Implementation |
| Jetty | 11.0.15 | Embedded Servlet Container |
| Jackson | 2.15.2 | JSON Processing |
| Maven | 3.6+ | Build Tool |

---

## Setup Instructions

### Prerequisites
- JDK 11 or higher
- Maven 3.6+ (or use Maven wrapper)
- Git (optional)

---

### Step 1: Clone or Download
```bash
git clone https://github.com/yourusername/smart-campus-api.git
cd smart-campus-api
```

---

### Step 2: Build the Project
```bash
mvn clean compile
```

---

### Step 3: Run the Server

#### Option 1 - Maven:
```bash
mvn exec:java -Dexec.mainClass="com.smartcampus.Main"
```

#### Option 2 - IntelliJ IDEA:
Open project in IntelliJ  
Right-click on src/main/java/com/smartcampus/Main.java  
Select Run 'Main.main()'

#### Option 3 - Java command:
```bash
java -cp target/classes com.smartcampus.Main
```

---

### Step 4: Verify Server is Running
You should see:

```
========================================
Smart Campus API Server Started!
Base URL: http://localhost:8080/api/v1
Discovery: http://localhost:8080/api/v1
========================================
```

---

## API Endpoints

### Base URL
```
http://localhost:8080/api/v1
```

---

### Part 1: Discovery

| Method | Endpoint | Description |
|--------|---------|------------|
| GET | /api/v1 | API metadata and HATEOAS links |

---

### Part 2: Room Management

| Method | Endpoint | Description |
|--------|---------|------------|
| GET | /api/v1/rooms | List all rooms |
| POST | /api/v1/rooms | Create a new room |
| GET | /api/v1/rooms/{roomId} | Get room by ID |
| DELETE | /api/v1/rooms/{roomId} | Delete room (fails if has sensors) |

---

### Part 3: Sensor Management

| Method | Endpoint | Description |
|--------|---------|------------|
| GET | /api/v1/sensors | List all sensors (supports ?type= filter) |
| POST | /api/v1/sensors | Register a new sensor |
| GET | /api/v1/sensors/{sensorId} | Get sensor by ID |

---

### Part 4: Sensor Readings (Sub-resources)

| Method | Endpoint | Description |
|--------|---------|------------|
| GET | /api/v1/sensors/{sensorId}/readings | Get reading history |
| POST | /api/v1/sensors/{sensorId}/readings | Add new reading |

---

## cURL Commands

### Part 1: Discovery (GET)
```bash
curl -X GET http://localhost:8080/api/v1
```

Expected Response:

```json
{
  "version": "1.0.0",
  "apiName": "Smart Campus Sensor & Room Management API",
  "contact": "admin@smartcampus.com",
  "resources": {
    "rooms": "/api/v1/rooms",
    "sensors": "/api/v1/sensors"
  },
  "_links": {
    "self": "/api/v1",
    "rooms_collection": "/api/v1/rooms",
    "sensors_collection": "/api/v1/sensors"
  }
}
```

---

### Part 2: Room Management

#### Create a Room (POST)
```bash
curl -X POST http://localhost:8080/api/v1/rooms \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Computer Lab 101",
    "building": "Engineering Building",
    "floor": 2,
    "roomType": "Computer Lab"
  }'
```

Expected Response (201 Created):

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Computer Lab 101",
  "building": "Engineering Building",
  "floor": 2,
  "roomType": "Computer Lab",
  "sensorIds": []
}
```

---

#### Get All Rooms (GET)
```bash
curl -X GET http://localhost:8080/api/v1/rooms
```

Expected Response (200 OK):

```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Computer Lab 101",
    "building": "Engineering Building",
    "floor": 2,
    "roomType": "Computer Lab",
    "sensorIds": []
  }
]
```

---

#### Get Room by ID (GET)
```bash
curl -X GET http://localhost:8080/api/v1/rooms/{roomId}
```

Expected Response (200 OK):

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Computer Lab 101",
  "building": "Engineering Building",
  "floor": 2,
  "roomType": "Computer Lab",
  "sensorIds": []
}
```

---

#### Delete Room (DELETE)
```bash
curl -X DELETE http://localhost:8080/api/v1/rooms/{roomId}
```

Expected Response (204 No Content) - if room has no sensors

---

### Part 3: Sensor Management

#### Create a Sensor (POST)
```bash
curl -X POST http://localhost:8080/api/v1/sensors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "CO2 Monitor A1",
    "type": "CO2",
    "roomId": "{roomId}",
    "unit": "ppm",
    "status": "ACTIVE"
  }'
```

Expected Response (201 Created):

```json
{
  "id": "660e8400-e29b-41d4-a716-446655440001",
  "name": "CO2 Monitor A1",
  "type": "CO2",
  "roomId": "550e8400-e29b-41d4-a716-446655440000",
  "unit": "ppm",
  "status": "ACTIVE",
  "currentValue": 0.0,
  "readings": []
}
```

---
---

#### Get All Sensors (GET)
```bash
curl -X GET http://localhost:8080/api/v1/sensors
```

---

#### Filter Sensors by Type (GET with QueryParam)
```bash
curl -X GET "http://localhost:8080/api/v1/sensors?type=CO2"
```

Expected Response (200 OK):

```json
[
  {
    "id": "660e8400-e29b-41d4-a716-446655440001",
    "name": "CO2 Monitor A1",
    "type": "CO2",
    "roomId": "550e8400-e29b-41d4-a716-446655440000",
    "unit": "ppm",
    "status": "ACTIVE",
    "currentValue": 0.0,
    "readings": []
  }
]
```

---

#### Get Sensor by ID (GET)
```bash
curl -X GET http://localhost:8080/api/v1/sensors/{sensorId}
```

---

### Part 4: Sensor Readings

#### Get Reading History (GET)
```bash
curl -X GET http://localhost:8080/api/v1/sensors/{sensorId}/readings
```

Expected Response (200 OK):

```json
[
  {
    "id": "770e8400-e29b-41d4-a716-446655440002",
    "value": 425.5,
    "timestamp": "2026-04-24T10:30:00",
    "notes": "Normal CO2 reading"
  }
]
```

---

#### Add a New Reading (POST)
```bash
curl -X POST http://localhost:8080/api/v1/sensors/{sensorId}/readings \
  -H "Content-Type: application/json" \
  -d '{
    "value": 425.5,
    "notes": "Normal CO2 reading"
  }'
```

Expected Response (201 Created):

```json
{
  "id": "770e8400-e29b-41d4-a716-446655440002",
  "value": 425.5,
  "timestamp": "2026-04-24T10:30:00",
  "notes": "Normal CO2 reading"
}
```

---

### Part 5: Error Handling Tests

#### 409 Conflict - Delete Room with Sensors
```bash
curl -X DELETE http://localhost:8080/api/v1/rooms/{roomWithSensorsId}
```

Expected Response (409 Conflict):

```json
{
  "status": 409,
  "error": "Conflict",
  "message": "Cannot delete room with active sensors. Remove all sensors first.",
  "timestamp": "2026-04-24T10:30:00",
  "path": "/api/v1/rooms/550e8400-e29b-41d4-a716-446655440000"
}
```

---

#### 422 Unprocessable - Create Sensor with Invalid Room ID
```bash
curl -X POST http://localhost:8080/api/v1/sensors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Invalid Sensor",
    "type": "TEMP",
    "roomId": "invalid-id-12345",
    "unit": "C",
    "status": "ACTIVE"
  }'
```

Expected Response (422 Unprocessable Entity):

```json
{
  "status": 422,
  "error": "Unprocessable Entity",
  "message": "Room with ID invalid-id-12345 does not exist",
  "timestamp": "2026-04-24T10:30:00",
  "path": "/api/v1/sensors"
}
```

---

#### 403 Forbidden - Add Reading to Maintenance Sensor
First, create a sensor with status "MAINTENANCE", then:

```bash
curl -X POST http://localhost:8080/api/v1/sensors/{sensorId}/readings \
  -H "Content-Type: application/json" \
  -d '{
    "value": 100.0,
    "notes": "Test reading"
  }'
```

Expected Response (403 Forbidden):

```json
{
  "status": 403,
  "error": "Forbidden",
  "message": "Sensor is in MAINTENANCE mode and cannot accept new readings",
  "timestamp": "2026-04-24T10:30:00",
  "path": "/api/v1/sensors/660e8400-e29b-41d4-a716-446655440001/readings"
}
```

---

#### 404 Not Found - Invalid Room ID
```bash
curl -X GET http://localhost:8080/api/v1/rooms/invalid-id-12345
```

Expected Response (404 Not Found):

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Room not found: invalid-id-12345",
  "timestamp": "2026-04-24T10:30:00",
  "path": "/api/v1/rooms/invalid-id-12345"
}
```

---

#### 415 Unsupported Media Type - Wrong Content-Type
```bash
curl -X POST http://localhost:8080/api/v1/rooms \
  -H "Content-Type: text/plain" \
  -d "invalid data"
```

Expected Response (415 Unsupported Media Type) - JAX-RS built-in response

---

#### 500 Internal Server Error - Global Safety Net
When any unexpected exception occurs (NullPointerException, etc.), the GlobalExceptionMapper catches it and returns:

```json
{
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred. Please try again later.",
  "timestamp": "2026-04-24T10:30:00",
  "path": "/api/v1/example"
}
```

Important: No stack trace is exposed to the client for security reasons.

---

## Sample Test Sequence (Copy-Paste Ready)

Run these commands in order:

```bash
# 1. Discovery
curl http://localhost:8080/api/v1

# 2. Create a room (SAVE THE ID FROM RESPONSE)
curl -X POST http://localhost:8080/api/v1/rooms -H "Content-Type: application/json" -d "{\"name\":\"Lab 101\",\"building\":\"Engineering\",\"floor\":1,\"roomType\":\"Laboratory\"}"

# 3. Get all rooms
curl http://localhost:8080/api/v1/rooms

# 4. Create a sensor (USE THE ROOM ID FROM STEP 2)
curl -X POST http://localhost:8080/api/v1/sensors -H "Content-Type: application/json" -d "{\"name\":\"CO2 Sensor\",\"type\":\"CO2\",\"roomId\":\"YOUR_ROOM_ID\",\"unit\":\"ppm\",\"status\":\"ACTIVE\"}"

# 5. Get sensors with filter
curl "http://localhost:8080/api/v1/sensors?type=CO2"

# 6. Add a reading
curl -X POST http://localhost:8080/api/v1/sensors/YOUR_SENSOR_ID/readings -H "Content-Type: application/json" -d "{\"value\":425.5,\"notes\":\"Normal reading\"}"

# 7. Get reading history
curl http://localhost:8080/api/v1/sensors/YOUR_SENSOR_ID/readings

# 8. Try to delete room with sensor (should fail with 409)
curl -X DELETE http://localhost:8080/api/v1/rooms/YOUR_ROOM_ID

# 9. Try invalid room ID (should fail with 422)
curl -X POST http://localhost:8080/api/v1/sensors -H "Content-Type: application/json" -d "{\"name\":\"Bad\",\"type\":\"TEMP\",\"roomId\":\"invalid\",\"unit\":\"C\"}"
```

---

## Postman Collection

### Import Instructions
Open Postman  
Click Import → Raw Text  
Copy the collection below  

---

### Collection JSON
```json
{
  "info": {
    "name": "Smart Campus API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "1. Discovery",
      "item": [
        {
          "name": "GET Discovery",
          "request": {
            "method": "GET",
            "header": [],
            "url": {
              "raw": "http://localhost:8080/api/v1",
              "protocol": "http",
              "host": ["localhost"],
              "port": "8080",
              "path": ["api", "v1"]
            }
          }
        }
      ]
    }
  ]
}
```

---

## Environment Setup in Postman

Click Environments → Create Environment  

Name: Smart Campus Local  

Add variables:

| Variable | Initial Value |
|----------|--------------|
| baseUrl | http://localhost:8080/api/v1 |
| roomId | (leave empty, fill from response) |
| sensorId | (leave empty, fill from response) |

---

## Project Structure
```text
smart-campus-api/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── smartcampus/
                    ├── Main.java
                    ├── ApplicationConfig.java
                    ├── DataStore.java
                    ├── models/
                    │   ├── SensorRoom.java
                    │   ├── Sensor.java
                    │   ├── SensorReading.java
                    │   └── ErrorResponse.java
                    ├── resources/
                    │   ├── DiscoveryResource.java
                    │   ├── RoomResource.java
                    │   ├── SensorResource.java
                    │   └── SensorReadingResource.java
                    ├── exceptions/
                    │   ├── RoomNotEmptyException.java
                    │   ├── ResourceNotFoundException.java
                    │   └── SensorUnavailableException.java
                    ├── mappers/
                    │   ├── RoomNotEmptyExceptionMapper.java
                    │   ├── ResourceNotFoundExceptionMapper.java
                    │   ├── SensorUnavailableExceptionMapper.java
                    │   └── GlobalExceptionMapper.java
                    └── filters/
                        └── LoggingFilter.java
```

---

## Error Response Format

All error responses follow this consistent JSON format:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Detailed error description",
  "timestamp": "2026-04-24T10:30:00",
  "path": "/api/v1/endpoint"
}
```

---

## HTTP Status Codes Used

| Status Code | Meaning | Usage |
|------------|--------|------|
| 200 | OK | Successful GET requests |
| 201 | Created | Successful POST requests |
| 204 | No Content | Successful DELETE requests |
| 400 | Bad Request | Malformed request |
| 403 | Forbidden | Sensor in maintenance mode |
| 404 | Not Found | Resource doesn't exist |
| 409 | Conflict | Delete room with sensors |
| 415 | Unsupported Media Type | Wrong Content-Type |
| 422 | Unprocessable Entity | Invalid roomId reference |
| 500 | Internal Server Error | Unexpected errors (no stack trace) |

---

## Logging Output Example

When the server is running, you'll see logs like:

```text
INFO: REQUEST: GET /api/v1
INFO: RESPONSE: 200 for GET /api/v1
INFO: REQUEST: POST /api/v1/rooms
INFO: RESPONSE: 201 for POST /api/v1/rooms
INFO: REQUEST: GET /api/v1/rooms/550e8400-e29b-41d4-a716-446655440000
INFO: RESPONSE: 200 for GET /api/v1/rooms/550e8400-e29b-41d4-a716-446655440000
INFO: REQUEST: DELETE /api/v1/rooms/550e8400-e29b-41d4-a716-446655440000
INFO: RESPONSE: 409 for DELETE /api/v1/rooms/550e8400-e29b-41d4-a716-446655440000
```

---

## Troubleshooting

### Issue: Port 8080 already in use

#### Windows:
```cmd
netstat -ano | findstr :8080
taskkill /PID [PID] /F
```

#### Mac/Linux:
```bash
lsof -i :8080
kill -9 [PID]
```

---

### Issue: mvn command not found
Use Maven wrapper instead:

```bash
# Windows
.\mvnw clean compile

# Mac/Linux
./mvnw clean compile
```

---

### Issue: curl not recognized
Use PowerShell or IntelliJ's built-in HTTP Client.

---

### Issue: Server starts but endpoints return 404
Check that all @Path annotations include /api/v1  

Verify Main.java registers all resource classes  

Check IntelliJ console for any startup errors  

---

### Issue: JSON parsing errors
Ensure:

Content-Type header is application/json  

JSON is valid (no trailing commas)  

Double quotes around property names and string values  

---

## Video Demonstration Checklist

For your 10-minute video demonstration, show:

Server starting up in IntelliJ/terminal  

GET /api/v1 - Discovery endpoint  

POST /api/v1/rooms - Create room (201 Created)  

GET /api/v1/rooms - List all rooms (200 OK)  

POST /api/v1/sensors - Create sensor (201 Created)  

GET /api/v1/sensors?type=CO2 - Filter by type (200 OK)  

POST /api/v1/sensors/{id}/readings - Add reading (201 Created)  

GET /api/v1/sensors/{id}/readings - Get history (200 OK)  

DELETE /api/v1/rooms/{id} - Delete room with sensors (409 Conflict)  

POST /api/v1/sensors - Invalid roomId (422 Unprocessable)  

Any endpoint showing clean 500 error (no stack trace)  

Important: Speak clearly and explain what each test demonstrates!

---

## Submission Requirements

According to the coursework specification:

Public GitHub Repository containing all project files  

README.md with:

API overview  

Step-by-step build and run instructions  

At least 5 sample cURL commands  

Video Demonstration (max 10 minutes) uploaded to Blackboard  

You must be present and speak clearly  

Show Postman tests only (no coding part)  

No ZIP files - GitHub link only  

No Database - Only in-memory data structures (HashMap, ArrayList)  

No Spring Boot - Only JAX-RS

