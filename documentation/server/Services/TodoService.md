# TodoService

Contains all the functions that operate on the **Todo** entity

## Create New Todo

### Logic

- Set Todo **title**
- Set Todo **description**
- Set **done** as **false**
- Save new todo

### Authorization

Todoer: ROLE_TODOER

```java
TodoDTO createTodo(TodoDTO todoDTO)
```



## Delete Todo

delete an existing todo

### Logic

- If ROLE is ROLE_ADMIN
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  - Delete Todo 
- If ROLE is ROLE_TODOER
  - Check if todoId is assigned to the calling Todoer
    - if not, return [AccessDeniedException](./Exceptions/AccessDeniedException.md#-)
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  - Delete Todo 

### Authorization

Admin: ROLE_ADMIN

Todoer: ROLE_TODOER

```java
TodoDTO deleteTodo(Long todoId)
```

## Get one Todo

Return a Todo given its id

### Logic

- If ROLE is ROLE_ADMIN
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  - Return Todo 
- If ROLE is ROLE_TODOER
  - Check if todoId is assigned to the calling Todoer
    - if not, return [AccessDeniedException](./Exceptions/AccessDeniedException.md#-)
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  - Return Todo 

### Authorization

Admin: ROLE_ADMIN

Todoer: ROLE_TODOER

```java
TodoDTO getTodo(Long todoId)
```



## Set Todo as done

### Logic

- If ROLE is ROLE_ADMIN
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  - Set done as TRUE
- If ROLE is ROLE_TODOER
  - Check if todoId is assigned to the calling Todoer
    - if not, return [AccessDeniedException](./Exceptions/AccessDeniedException.md#-)
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  - Set done as TRUE

### Authorization

Admin: ROLE_ADMIN

Todoer: ROLE_TODOER

```java
TodoDTO setTodoAsDone(Long todoId)
```

## Reset Todo state

### Logic

- If ROLE is ROLE_ADMIN
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  - Set done as FALSE
- If ROLE is ROLE_TODOER
  - Check if todoId is assigned to the calling Todoer
    - if not, return [AccessDeniedException](./Exceptions/AccessDeniedException.md#-)
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  - Set done as FALSE

### Authorization

Admin: ROLE_ADMIN

Todoer: ROLE_TODOER

```java
TodoDTO resetTodo(Long todoId)
```

## Modify Todo 

### Logic

- If ROLE is ROLE_ADMIN
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  -  Modify todo
- If ROLE is ROLE_TODOER
  - Check if todoId is assigned to the calling Todoer
    - if not, return [AccessDeniedException](./Exceptions/AccessDeniedException.md#-)
  - Check if todoId corresponds to an existing Todo
    - if not return [TodoNotFoundException](./Exceptions/TodoNotFoundException.md#-)
  - Modify todo

### Authorization

Admin: ROLE_ADMIN

Todoer: ROLE_TODOER

```java
TodoDTO modifyTodo(Long todoId, String title, String description)
```

