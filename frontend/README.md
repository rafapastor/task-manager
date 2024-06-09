# Task Manager Frontend

This is the frontend for a task management application, developed with Vue.js and TypeScript. The application allows you to create, update, list, and delete tasks.

## Requirements

- **Node.js** (version 14.x or higher)
- **Yarn** (version 1.22.x or higher)

## Project setup

```
yarn install
```

### Compiles and hot-reloads for development

```
yarn serve
```

### Compiles and minifies for production

```
yarn build
```

### Run your unit tests

```
yarn test:unit
```

### Lints and fixes files

```
yarn lint
```

### Project Structure

- **`src**`: Main source code directory.
  - **`assets**`: Static assets like CSS, images, etc.
  - **`components**`: Vue components.
  - **`composables**`: Composable functions for reusable logic.
  - **`types**`: TypeScript type definitions.
- **`public**`: Public static files.

### API Integration

The frontend communicates with the backend API using Axios. The base URL for the API is configured in axiosConfig.js.

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

### Get all Tasks

- **URL: `/tasks`**
- **Method: `GET`**
- **Response**

```bash
[
  {
    "id": 1,
    "title": "Task Title",
    "description": "Task Description",
    "dueDate": "YYYY-MM-DD",
    "status": "pending"
  },
  ...
]
```

### Update a Task

- **URL: `/tasks/{id}`**
- **Method: `PUT`**
- **Request Body**

```bash
{
  "title": "Updated Task Title",
  "description": "Updated Task Description",
  "dueDate": "YYYY-MM-DD",
  "status": "completed" // or "pending"
}

```

- **Response**

```bash
{
  "id": 1,
  "title": "Updated Task Title",
  "description": "Updated Task Description",
  "dueDate": "YYYY-MM-DD",
  "status": "completed"
}
```

### Delete a Task

- **URL: `/tasks/{id}`**
- **Method: `DELETE`**
