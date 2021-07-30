# /api/v1/todoer

## /

### GET

Return the list of all todoers

Success:

- **200:** return the list of todoers 

```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Foe",
    "username": "johnfoe",
    "email": "johnfoe@todo.it"
  },
  {
    "id": 2,
    "firstName": "Boris",
    "lastName": "Flick",
    "username": "borisflick",
    "email": "borisflick@todo.it"
  }
]
```

List of error codes:

- **401:** user is not logged in

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "User is not logged in",
    "path": "/api/v1/todoer"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/todoer"
  }
  ```

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todoer"
  }
  ```

Summary

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **500:** Internal server error

### POST

Create a new todoer

Body:

```json
{
  "firstName": "John",
  "lastName": "Foe",
  "username": "johnfoe",
  "email": "johnfoe@todo.it"
  "password": "xxxxxxxx"
}
```



Success:

- **200:** return the new created todoer

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Foe",
  "username": "johnfoe",
  "email": "johnfoe@todo.it"
}
```

List of error codes:

- **400:** one or more fields is empty or not valid

  ```json
  {
    "timestamp": "2021-05-18T13:43:59.847+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "User's firstName cannot be empty",
    "validationErrors": [ "User's firstName cannot be empty" ],
    "path": "/api/v1/todoer"
  }
  ```
  
- **409:** username and/or email are already used

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 409,
    "error": "Conflict",
    "message": "Username and/or email already used",
    "path": "/api/v1/todoer"
  }
  ```

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todoer"
  }
  ```

  

Summary:

- **400:** one or more fields is empty or not valid
- **409:** username and/or email are already used
- **500:** Internal server error

## /{todoerId}

### GET

Return details about one todoer

Success:

- **200:** return the requested todoer

  ```json
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Foe",
    "username": "johnfoe",
    "email": "johnfoe@todo.it"
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
    "path": "/api/v1/todoer/2"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/todoer/2"
  }
  ```

  

- **404:** todoer is not found

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 404,
    "error": "Not found",
    "message": "Todoer not found",
    "path": "/api/v1/todoer/2"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todoer/2"
  }
  ```

   

Summary:

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **404:** todoer is not found
- **500:** Internal server error 

### DELETE

Delete one todoer

Success:

- **200:** return the deleted todoer

  ```json
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Foe",
    "username": "johnfoe",
    "email": "johnfoe@todo.it"
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
    "path": "/api/v1/todoer/2"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/todoer/2"
  }
  ```

  

- **404:** todoer is not found

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 404,
    "error": "Not found",
    "message": "Todoer not found",
    "path": "/api/v1/todoer/2"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todoer/2"
  }
  ```

   

Summary:

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **404:** todoer is not found
- **500:** Internal server error 

## /{todoerId}/todo

### GET

Return the list of todo associated to todoerId

Success:

- **200:** return the list of todos 

```json
[
  {
    "id": 3,
    "title": "Homework",
    "description": "Do homework",
    "done": false
  },
  {
    "id": 2,
    "title": "Food",
    "description": "Buy food",
    "done": false
  }
]
```

List of error codes:

- **401:** user is not logged in

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "User is not logged in",
    "path": "/api/v1/todoer/2/todo"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/todoer/2/todo"
  }
  ```

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/todoer/2/todo"
  }
  ```

Summary

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **500:** Internal server error