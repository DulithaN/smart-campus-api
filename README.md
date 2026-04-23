# Smart Campus Sensor & Room Management API

## Overview

This RESTful API provides comprehensive management for a university's "Smart Campus" infrastructure, handling rooms, sensors, and sensor readings. Built with JAX-RS (Jersey) following REST principles and HATEOAS.

## Technology Stack

- Java 11
- JAX-RS (Jakarta RESTful Web Services) 3.1.0
- Jersey 3.1.3 (JAX-RS Implementation)
- Jetty 11.0.15 (Embedded Servlet Container)
- Jackson (JSON Processing)
- Java Util Logging

## Setup Instructions

### Prerequisites

- JDK 11 or higher
- Maven 3.6+
- Git

### Build and Run

```bash
# Clone the repository
git clone https://github.com/yourusername/smart-campus-api.git
cd smart-campus-api

# Build the project
mvn clean compile

# Run with Jetty
mvn jetty:run

# OR run with Main class
mvn exec:java -Dexec.mainClass="com.smartcampus.Main"