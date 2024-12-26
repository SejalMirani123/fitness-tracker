# fitness-tracker


# Project Overview
The Fitness Tracker API is a Spring Boot-based application designed to facilitate fitness management. 
It supports user registration, workout tracking, and activity logging while providing robust security and 
API documentation through Spring Security and Swagger UI.



# Features which Includes
- User Registration 
- CRUD operations
- Authentication using Spring Security
- API documentation with Swagger/OpenAPI
- Modular architecture for scalability



# Version

- Java: 21+
- Gradle:8.2.1
- Database:MySQL
- SpringBoot:3.3.3


# API Documentation
- Swagger UI: (http://localhost:8080/api/swagger-ui/index.html)
- OpenAPI Docs: (http://localhost:8080/api/v3/api-docs)



# Security Configuration
The API uses Spring Security with the following configuration:

- authentication 
- Permit access to Swagger endpoints 
- Secure user, workout, and activity endpoint


# Swagger UI Not Accessible
1. Verify `springdoc.swagger-ui.enabled=true` in `application.yml`.
2. Ensure the application context path (`/api`) is part of the Swagger URL:
    

# Database Connectivity Issues
1. Confirm database credentials in `application.yml`.
2. Ensure the database server is running with creating schema.

# Authentication Errors
1. Verify authentication token configuration.
2. Check Spring Security rules in `SecurityConfig.java`.

# Jacoco 
1. added jacoco also for the test cases coverage.
 

# Contact
# please contact for the queries: **[sejalmirani2019@gmail.com].**
