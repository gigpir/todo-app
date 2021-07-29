# /api/v1/admin

## /

### GET

Return the list of admins registered in the platform

Success:

- **200:** return the list of admins 

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
    "path": "/api/v1/admin"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
    "path": "/api/v1/admin"
  }
  ```

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/admin"
  }
  ```

Summary

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **500:** Internal server error

### POST

Create a new admin

Body:

```json
{
  "firstName": "John",
  "lastName": "Foe",
  "username": "johnfoe",
  "email": "johnfoe@todo.it",
  "password": "xxxxxxxx"
}
```

Success:

- **200:** return the new created admin

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
    "validationErrors": [
      "User's firstName cannot be empty"
    ],
    "path": "/api/v1/admin"
  }
  ```

- **401:** user is not logged in

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "User is not logged in",
    "path": "/api/v1/admin"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
  	"path": "/api/v1/admin"
  }
  ```

  

- **409:** username and/or email are already used

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 409,
    "error": "Conflict",
    "message": "Username and/or email already used",
  	"path": "/api/v1/admin"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/admin"
  }
  ```

  

Summary:

- **400:** one or more fields is empty or not valid
- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **409:** username and/or email are already used
- **500:** Internal server error

## /{adminId}

### GET

Return details about one admin

Success:

- **200:** return the requested admin

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
  	"path": "/api/v1/admin/2"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
  	"path": "/api/v1/admin/2"
  }
  ```

  

- **404:** admin is not found

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 404,
    "error": "Not found",
    "message": "Admin not found",
  	"path": "/api/v1/admin/2"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/admin/2"
  }
  ```

   

Summary:

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **404:** admin is not found
- **500:** Internal server error 

### DELETE

Delete one admin

Success:

- **200:** return the deleted admin

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
  	"path": "/api/v1/admin/2"
  }
  ```

  

- **403:** User has no permission to access to the resource

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 403,
    "error": "Forbidden",
    "message": "Access denied",
  	"path": "/api/v1/admin/2"
  }
  ```

  

- **404:** admin is not found

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 404,
    "error": "Not found",
    "message": "Admin not found",
  	"path": "/api/v1/admin/2"
  }
  ```

  

- **500:** Internal server error

  ```json
  {
    "timestamp": "2021-05-17T20:54:23.708+00:00",
    "status": 500,
    "error": "Internal server error",
    "message": "Internal server error",
    "path": "/api/v1/admin/2"
  }
  ```

   

Summary:

- **401:** user is not logged in
- **403:** User has no permission to access to the resource
- **404:** admin is not found
- **500:** Internal server error 



