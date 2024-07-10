# Spring Boot Task Management Application

*disclaimer: some parts of this markdown are intentionally generated with chatgpt

## Overview

This is a simple Task Management web application built with Spring Boot. The application allows users to create, update, delete, and retrieve tasks. Each user can only manage their own tasks. The application includes authentication using Spring Security.

## Features

- Create a new task
- Update the status of a task
- Retrieve all tasks for the authenticated user
- Retrieve tasks by status
- Delete a task

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Security
- JUnit 5
- Mockito

## Setup Instructions

### Prerequisites

- JDK 17 or higher
- Maven
- Git

### Clone the Repository

```bash
git clone https://github.com/yourusername/taskmanagement.git
cd taskmanagement
```

### Build the Project
```bash
mvn clean install
```

### Run the Application
```
mvn spring-boot:run
```

The application will start on http://localhost:8080.

Default User

A default user is created when the application starts:
(to make testing easier)

- Username: user1
- Password: password

### API Endpoints

#### Authentication
 Use `Basic Auth` with the provided username and password to authenticate. (I used postman for this)

#### Task Endpoints
1. Create a Task

- Endpoint: POST /api/tasks
- Request Body:
```json
{
  "title": "Task Title",
  "description": "Task Description",
  "status": "Pending"
}
```

- Response:
```json
{
  "id": 1,
  "title": "Task Title",
  "description": "Task Description",
  "status": "Pending",
  "creatorId": "user1"
}
```

2. Update Task Status

- Endpoint: PUT /api/tasks/{id}/status
- Request Body:
```json
completed
```

- Response:
```json
{
    "id": 1,
    "title": "Task Title",
    "description": "Task Description",
    "status": "completed",
    "creatorId": "user1"
}
```

3. Get All Tasks
- Endpoint: GET /api/tasks
- Response:
```json
[
  {
    "id": 1,
    "title": "Task Title",
    "description": "Task Description",
    "status": "completed",
    "creatorId": "user1"
  }
]
```

4.Get Tasks by Status

- Endpoint: GET /api/tasks/status/{status}
example : completed
- Response:
```json
[
  {
    "id": 1,
    "title": "Task Title",
    "description": "Task Description",
    "status": "Pending",
    "creatorId": "user1"
  }
]
```

5. Delete a Task

- Endpoint: DELETE /api/tasks/{id}
 Response: 200 OK

 ## FUTURE ENCHANCEMENTS

 - Add more tests (edge cases, etc)
 - Add more customized exceptions
 - Implement security best practices
 - Deploy to Heroku


```tested: working well 14.41 WITA 10/07/2024```