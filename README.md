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

**Option 1 - Maven:**
```bash
mvn exec:java -Dexec.mainClass="com.smartcampus.Main"
```

**Option 2 - IntelliJ IDEA:**
Open project → Run `Main.java`

**Option 3 - Java command:**
```bash
java -cp target/classes com.smartcampus.Main
```

---

### Step 4: Verify Server is Running
```
========================================
Smart Campus API Server Started!
Base URL: http://localhost:8080/api/v1
========================================
```

---

## API Endpoints

### Base URL
```
http://localhost:8080/api/v1
```

---

### Discovery
| Method | Endpoint | Description |
|--------|----------|------------|
| GET | /api/v1 | API metadata and HATEOAS links |

---

### Room Management
| Method | Endpoint | Description |
|--------|----------|------------|
| GET | /api/v1/rooms | List all rooms |
| POST | /api/v1/rooms | Create a new room |
| GET | /api/v1/rooms/{roomId} | Get room by ID |
| DELETE | /api/v1/rooms/{roomId} | Delete room |

---

### Sensor Management
| Method | Endpoint | Description |
|--------|----------|------------|
| GET | /api/v1/sensors | List all sensors |
| POST | /api/v1/sensors | Register a new sensor |
| GET | /api/v1/sensors/{sensorId} | Get sensor by ID |

---

### Sensor Readings
| Method | Endpoint | Description |
|--------|----------|------------|
| GET | /api/v1/sensors/{sensorId}/readings | Get reading history |
| POST | /api/v1/sensors/{sensorId}/readings | Add new reading |

---

## Sample cURL Commands

### Discovery
```bash
curl http://localhost:8080/api/v1
```

### Create Room
```bash
curl -X POST http://localhost:8080/api/v1/rooms -H "Content-Type: application/json" -d "{\"name\":\"Lab 101\",\"building\":\"Engineering\",\"floor\":1}"
```

### Get Rooms
```bash
curl http://localhost:8080/api/v1/rooms
```

### Create Sensor
```bash
curl -X POST http://localhost:8080/api/v1/sensors -H "Content-Type: application/json" -d "{\"name\":\"CO2 Sensor\",\"type\":\"CO2\",\"roomId\":\"YOUR_ROOM_ID\"}"
```

### Add Reading
```bash
curl -X POST http://localhost:8080/api/v1/sensors/YOUR_SENSOR_ID/readings -H "Content-Type: application/json" -d "{\"value\":425.5}"
```

---

## Error Response Format
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

## HTTP Status Codes

| Code | Meaning |
|------|--------|
| 200 | OK |
| 201 | Created |
| 204 | No Content |
| 400 | Bad Request |
| 403 | Forbidden |
| 404 | Not Found |
| 409 | Conflict |
| 415 | Unsupported Media Type |
| 422 | Unprocessable Entity |
| 500 | Internal Server Error |

---

## Project Structure
```
smart-campus-api/
├── pom.xml
├── README.md
└── src/main/java/com/smartcampus/
```

---

## Troubleshooting

### Port 8080 already in use
```bash
netstat -ano | findstr :8080
taskkill /PID [PID] /F
```

### Maven not found
```bash
.\mvnw clean compile
```

---

## Video Demonstration Checklist
- Start server
- Test endpoints using Postman
- Show success + error cases
- Explain clearly

---

## Submission Requirements
- Public GitHub repository  
- README.md with setup + cURL  
- Video demo (max 10 minutes)  
- No database (in-memory only)  
- No Spring Boot (JAX-RS only)
