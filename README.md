# Task Manager Application

This repository contains both the frontend and backend for a task management application. The backend is developed with Spring Boot and stores data in a JSON file, while the frontend is developed with Vue.js and TypeScript.

## Repository Structure

- `backend/`: Contains the Spring Boot application for the backend.
- `frontend/`: Contains the Vue.js application for the frontend.

## Requirements

- **Java 17** (for backend)
- **Maven 3.6.0 or higher** (for backend)
- **Node.js** (version 14.x or higher, for frontend)
- **Yarn** (version 1.22.x or higher, for frontend)

## Setup Instructions

### Clone the Repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd task-manager
```

## Setting Up the Backend

1. Navigate to the backend directory:

```bash
cd backend
```

2. Follow the instructions in the backend/README.md to set up and run the backend application.

## Setting Up the Frontend

1. Navigate to the backend directory:

```bash
cd frontend
```

2. Follow the instructions in the frontend/README.md to set up and run the backend application.

## Running the Full Application

### Start the Backend

Navigate to the **backend** directory and start the Spring Boot application:

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend application will start on **http://localhost:8080**

### Start the Frontend

Navigate to the **frontend** directory and start the Vue.js application:

```bash
cd frontend
yarn install
yarn serve
```

The frontend application will start on **http://localhost:8081**
