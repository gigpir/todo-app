# Todo Controller

Handle all requests to endpoint **/api/v1/todo**

```java
@RestController
@RequestMapping("/api/v1/todo")
public class TodoController{
  ...
}
```

## Create New Todo

create a new Todo

Handle the **POST** request  to the endpoint **/** ([REST API documentation](../REST API/api/v1/todo/todo.md#-))

### Logic

Call the method `createTodo()` of the [TodoService](../Services/TodoService.md#create-new-todo).

Map the following exceptions to the corresponding HTTP status codes:

* [TodoNotFoundException](../Services/Exceptions/TodoNotFoundException.md#-) -> **404**
*  [AccessDeniedException](../Services/Exceptions/AccessDeniedException.md#-) -> **403**

```java
@PostMapping("/")
public TodoDTO createTodo(@Valid @RequestBody TodoDTO todoDTO)
```

## Get one Todo

Return the details about one todo

Handle the **GET** request to the enpoint **/{todoId}** ([REST API documentation](../REST API/api/v1/todo/todo.md#-/))

### Logic

Call the method `getTodo()` of the [todoService](../Services/TodoService.md#get-one-todo)

Map the following exceptions to the corresponding HTTP status codes:

- [TodoNotFoundException](../Services/Exceptions/TodoNotFoundException.md#-)  -> **404**
- [AccessDeniedException](../Services/Exceptions/AccessDeniedException.md#-) -> **403**

```java
@GetMapping("/{todoId}")
public TodoDTO getTodo(@PathVariable ("todoId") Long todoId)
```

## Delete Todo

delete an extisting todo

Handle the **DELETE** request to the enpoint **/{todoId}** ([REST API documentation](../REST API/api/v1/todo/todo.md#delete-todo))

### Logic

Call the method `deleteTodo()` of the [todoService](../Services/TodoService.md#delete-todo)

Map the following exceptions to the corresponding HTTP status codes:

- [TodoNotFoundException](../Services/Exceptions/TodoNotFoundException.md#-) -> **404**
- [AccessDeniedException](../Services/Exceptions/AccessDeniedException.md#-) -> **403**

```java
@DeleteMapping("/{todoId}")
public TodoDTO deleteTodo(@PathVariable ("todoId") Long todoId)
```

## Modify Todo 

modify todo title and description

Handle the **PUT** request to the enpoint **/{todoId}** ([REST API documentation](../REST API/api/v1/todo/todo.md#modify-todo))

### Logic

Call the method `modifyTodo()` of the [todoService](../Services/TodoService.md#modify-todo)

Map the following exceptions to the corresponding HTTP status codes:

- [TodoNotFoundException](../Services/Exceptions/TodoNotFoundException.md#-) -> **404**
-  [AccessDeniedException](../Services/Exceptions/AccessDeniedException.md#-) -> **403**

```java
@PutMapping("/{todoId}")
public TodoDTO modifyTodo(@PathVariable ("todoId") Long todoId, String title, String description)
```



## Set Todo as done

change done state of a todo

Handle the **PUT** request to the enpoint **/{todoId}/done** ([REST API documentation](../REST API/api/v1/todo/todo.md#-/))

### Logic

Call the method `setTodoAsDone()` of the [todoService](../Services/TodoService.md#set-todo-as-done)

Map the following exceptions to the corresponding HTTP status codes:

- [TodoNotFoundException](../Services/Exceptions/TodoNotFoundException.md#-) -> **404**
- [AccessDeniedException](../Services/Exceptions/AccessDeniedException.md#-) -> **403**

```java
@PutMapping("/{todoId}/done")
public TodoDTO setTodoAsDone(@PathVariable ("todoId") Long todoId)
```

## Reset Todo state

change done state of a todo

Handle the **PUT** request to the enpoint **/{todoId}/reset** ([REST API documentation](../REST API/api/v1/todo/todo.md#-/))

### Logic

Call the method `resetTodo()` of the [todoService](../Services/TodoService.md#reset-todo-state)

Map the following exceptions to the corresponding HTTP status codes:

- [TodoNotFoundException](../Services/Exceptions/TodoNotFoundException.md#-) ->  **404**
-  [AccessDeniedException](../Services/Exceptions/AccessDeniedException.md#-) -> **403**

```java
@PutMapping("/{todoId}/reset")
public TodoDTO resetTodo(@PathVariable ("todoId") Long todoId)
```

