# Todoer Controller

Handle all requests to endpoint **/api/v1/todoer**

```java
@RestController
@RequestMapping("/api/v1/todoer")
public class TodoerController{
  ...
}
```

## Create new Todoer

create a new Todoer

Handle the **POST** request  to the endpoint **/** ([REST API documentation](../REST%20API/api/v1/todoer/todoer.md#-))

### Logic

Call the method `createTodoer()` of the [TodoerService](../Services/TodoerService.md#create-new-todoer).

Map the following exceptions to the corresponding HTTP status codes:

* [AdminNotFoundException](../Services/Exceptions/AdminNotFoundException.md#-), [EmailAlreadyExistException](../Services/Exceptions/EmailAlreadyExistException.md#-) -> **409**

```java
@PostMapping("/")
public TodoerDTO createTodoer(@Valid @RequestBody TodoerDTO todoerDTO)
```

## Get all Todoers

Return the list of all todoers

Handle the **GET** request  to the endpoint **/** ([REST API documentation](../REST%20API/api/v1/todoer/todoer.md#-))

### Logic

Call the method `getAllTodoers()` of the [TodoerService](../Services/TodoerService.md#get-all-todoers).

```java
@GetMapping("/")
public List<TodoerDTO> getAllTodoers()
```

## Get one Todoer

Return the details about one todoer

Handle the **GET** request to the enpoint **/{todoerId}** ([REST API documentation](../REST%20API/api/v1/todoer/todoer.md#-/))

### Logic

Call the method `getTodoer()` of the [todoerService](../Services/TodoerService.md#get-one-todoer)

Map the following exceptions to the corresponding HTTP status codes:

- [TodoerNotFoundException](../Services/Exceptions/TodoerNotFoundException.md#-)  -> **404**
-  [AccessDeniedException](../Services/Exceptions/AccessDeniedException.md#-) -> **403**

```java
@GetMapping("/{todoerId}")
public TodoerDTO getTodoer(@PathVariable ("todoerId") Long todoerId)
```

## Delete Todoer

Delete a Todoer 

Handle the **DELETE** request  to the endpoint **/{todoerId}** ([REST API documentation](../REST%20API/api/v1/todoer/todoer.md#-))

### Logic

Call the method `deleteTodoer()` of the [todoerService](../Services/TodoerService.md#delete-todoer)

Map the following exceptions to the corresponding HTTP status codes:

- [TodoerNotFoundException](../Services/Exceptions/TodoerNotFoundException.md#-) -> **404**
-  [AccessDeniedException](../Services/Exceptions/AccessDeniedException.md#-) -> **403**

```java
@DeleteMapping("/{todoerId}")
public TodoerDTO deleteTodoer(@PathVariable ("todoerId") Long todoerId)
```

## Get Todoer's Todos

Given a todoer's id, return all the corresponding Todos associated with that id

Handle the **GET** request  to the endpoint **{todoerId}/todo** ([REST API documentation](../REST%20API/api/v1/todoer/todoer.md#-))

### Logic

Call the method `getTodos()` of the [todoerService](../Services/TodoerService.md#Get-todoer's-todos)

Map the following exceptions to the corresponding HTTP status codes:

- [TodoerNotFoundException](../Services/Exceptions/TodoerNotFoundException.md#-) -> **404**
-  [AccessDeniedException](../Services/Exceptions/AccessDeniedException.md#-) -> **403**

```java
@GetMapping("/{todoerId}/todo")
public List<TodoDTO> getTodos(@PathVariable ("todoerId") Long todoerId)
```

