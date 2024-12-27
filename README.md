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


**Response-->**curl --location 'http://localhost:8080/api/workout' \
--header 'Content-Type: application/json' \
--data '{
  "id": 1,
  "name": "Morning Yoga",
  "description": "A calming yoga routine for beginners.",
  "difficulty": "Easy"
}
'
![image](https://github.com/user-attachments/assets/ad866956-d3c0-4b62-93f5-b9611d0e553b)




**Swagger sceenshot-->**
**http://localhost:8080/api/swagger-ui/index.html**
![image](https://github.com/user-attachments/assets/c8eae333-30bf-424c-ab0d-163288c9ff65)


**http://localhost:8080/api/v3/api-docs**
{
  "openapi": "3.0.1",
  "info": {
    "title": "Fitness Tracker API",
    "description": "API for managing workout plans, activity logs, and progress tracking.",
    "version": "1.0"
  },
  "servers": [
    {
      "url": "http://localhost:8080/api",
      "description": "Generated server url"
    }
  ],
  "paths": {
    "/workout": {
      "get": {
        "tags": [
          "workout-plan-controller"
        ],
        "operationId": "getWorkoutPlans",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/WorkoutPlan"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "workout-plan-controller"
        ],
        "operationId": "createWorkoutPlan",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/WorkoutPlan"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/WorkoutPlan"
                }
              }
            }
          }
        }
      }
    },
    "/users": {
      "get": {
        "tags": [
          "user-controller"
        ],
        "operationId": "getUsers",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/User"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "user-controller"
        ],
        "summary": "Create a new user",
        "description": "Adds a new user to the Fitness Tracker system.",
        "operationId": "createUser",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/User"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/activity": {
      "get": {
        "tags": [
          "activity-log-controller"
        ],
        "operationId": "getActivityLogs",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/ActivityLog"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "activity-log-controller"
        ],
        "operationId": "createActivityLog",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/ActivityLog"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ActivityLog"
                }
              }
            }
          }
        }
      }
    },
    "/workout/{id}": {
      "get": {
        "tags": [
          "workout-plan-controller"
        ],
        "operationId": "getWorkoutPlanById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/WorkoutPlan"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "workout-plan-controller"
        ],
        "operationId": "deleteWorkoutPlan",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/users/{id}": {
      "get": {
        "tags": [
          "user-controller"
        ],
        "operationId": "getUserById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/User"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "user-controller"
        ],
        "operationId": "deleteUser",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/activity/{id}": {
      "get": {
        "tags": [
          "activity-log-controller"
        ],
        "operationId": "getActivityLogById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/ActivityLog"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "activity-log-controller"
        ],
        "operationId": "deleteActivityLog",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "WorkoutPlan": {
        "required": [
          "name"
        ],
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "name": {
            "type": "string"
          },
          "description": {
            "type": "string"
          },
          "difficulty": {
            "type": "string"
          }
        }
      },
      "User": {
        "required": [
          "password",
          "username"
        ],
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "username": {
            "type": "string"
          },
          "password": {
            "type": "string"
          },
          "roles": {
            "type": "array",
            "items": {
              "type": "string"
            }
          }
        }
      },
      "ActivityLog": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "date": {
            "type": "string",
            "format": "date"
          },
          "user": {
            "$ref": "#/components/schemas/User"
          },
          "activityType": {
            "type": "string"
          },
          "duration": {
            "type": "integer",
            "format": "int32"
          }
        }
      }
    }
  }
}
