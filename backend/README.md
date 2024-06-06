# Task Manager Backend

This is the backend for a task management application, developed with Spring Boot. The application allows you to create, update, list, and delete tasks, and stores data in a JSON file.

## Requirements

- Java 17
- Maven 3.6.0 or higher

## Project Setup

### Clone the Repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd task-manager-backend
```

### Set Up the Environment

Ensure you have Java 17 and Maven installed on your system. You can check your Java and Maven versions with the following commands:

```bash
java -version
mvn -version
```

## Running the Application

### Build the Project

Use Maven to build the project:

```bash
mvn clean install
```

### Run the aplication

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start on **http://localhost:8080**

## API Endpoints

### Create a Task

- **URL: `/tasks`**
- **Method: `POST`**
- **Request Body**

```bash
{
  "title": "Task Title",
  "description": "Task Description",
  "dueDate": "YYYY-MM-DD"
}
```

- **Response**

```bash
{
  "id": 1,
  "title": "Task Title",
  "description": "Task Description",
  "dueDate": "YYYY-MM-DD",
  "status": "pending"
}

```
