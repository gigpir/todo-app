# /api/v1/todo

## /

### POST

Create a new todo

Body:

```json
{
  "title": "Homework",
  "description": "Do homework"
}
```

Success:

- **200:** return the new created todo

```json
{
  "id": 3,
  "title": "Homework",
  "description": "Do homework",
  "done": false
}
```

List of error codes:

- **400:** one or more fields is empty or not valid

  ```json
  {
    "timestamp": "2021-05-18T13:43:59.847+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "title cannot be empty",
    "validationErrors": [ "title cannot be empty" ],
    "path": "/api/v1/todo"
  }
  ```
  
- **401:** user is not logged in

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "User is not logged in",
    "path": "/api/v1/todo"
  }
  ```

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todo"
  }
  ```

  

Summary:

- **400:** one or more fields is empty or not valid
- **401:** user is not logged in
- **500:** Internal server error

## /{todoId}

### GET

Return details about a todo

Success:

- **200:** return the requested todo

  ```json
  {
    "id": 3,
    "title": "Homework",
    "description": "Do homework",
    "done": false
  }
  ```

  

List of error codes:

- **401:** user is not logged in

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "User is not logged in",
    "path": "/api/v1/todo/2"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/todo/2"
  }
  ```

  

- **404:** todo is not found

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 404,
    "error": "Not found",
    "message": "Todo not found",
    "path": "/api/v1/todo/2"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todo/2"
  }
  ```

   

Summary:

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **404:** todo is not found
- **500:** Internal server error 

### DELETE

Delete one todo

Success:

- **200:** return the deleted todo

  ```json
  {
    "id": 3,
    "title": "Homework",
    "description": "Do homework",
    "done": false
  }
  ```

List of error codes:

- **401:** user is not logged in

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "User is not logged in",
    "path": "/api/v1/todo/2"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/todo/2"
  }
  ```

  

- **404:** todo is not found

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 404,
    "error": "Not found",
    "message": "Todo not found",
    "path": "/api/v1/todo/2"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todo/2"
  }
  ```

   

Summary:

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **404:** todo is not found
- **500:** Internal server error 

### PUT

Modify Todo

Body:

```json
{
  "title": "Do Math",
  "description": "Do math homework"
}
```

Success:

- **200:** return the updated todo

  ```json
  {
    "id": 2,
    "title": "Do Math",
    "description": "Do math homework",
    "done": true
  }
  ```

List of error codes:



- **400:** one or more fields is empty or not valid

  ```json
  {
    "timestamp": "2021-05-18T13:43:59.847+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "title cannot be empty",
    "validationErrors": [ "title cannot be empty" ],
    "path": "/api/v1/todo/2"
  }
  ```
  
- **401:** user is not logged in

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "User is not logged in",
    "path": "/api/v1/todo/2"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/todo/2"
  }
  ```

  

- **404:** todo is not found

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 404,
    "error": "Not found",
    "message": "Todo not found",
  	"path": "/api/v1/todo/2"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todo/2"
  }
  ```

   

Summary:

- **400:** one or more fields is empty or not valid
- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **404:** todo is not found
- **500:** Internal server error 

## /{todoId}/done

### PUT

Set the done state of a todo to **true**

Success:

- **200:** return the updated todo

  ```json
  {
    "id": 2,
    "title": "Homework",
    "description": "Do homework",
    "done": true
  }
  ```

List of error codes:

- **401:** user is not logged in

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "User is not logged in",
    "path": "/api/v1/todo/2/done"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/todo/2/done"
  }
  ```

  

- **404:** todo is not found

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 404,
    "error": "Not found",
    "message": "Todo not found",
    "path": "/api/v1/todo/2/done"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todo/2/done"
  }
  ```

   

Summary:

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **404:** todo is not found
- **500:** Internal server error 

### 

## /{todoId}/reset

### PUT

Set the done state of a todo to **false**

Success:

- **200:** return the updated todo

  ```json
  {
    "id": 2,
    "title": "Homework",
    "description": "Do homework",
    "done": false
  }
  ```

List of error codes:

- **401:** user is not logged in

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "User is not logged in",
    "path": "/api/v1/todo/2/reset"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/todo/2/reset"
  }
  ```

  

- **404:** todo is not found

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 404,
    "error": "Not found",
    "message": "Todo not found",
    "path": "/api/v1/todo/2/reset"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todo/2/reset"
  }
  ```

   

Summary:

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **404:** todo is not found
- **500:** Internal server error 
