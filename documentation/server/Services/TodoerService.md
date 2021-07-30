# TodoerService

Contains all the functions that operate on the **Todoer** entity

## Create new Todoer

### Logic

- Check if the provided username is already used
  - if yes, it throws an exception [UsernameAlreadyExistException](./Exceptions/UsernameAlreadyExistException.md#-)
- Check if the provided email is already used
  - if yes, it throws an exception [EmailAlreadyExistException](./Exceptions/EmailAlreadyExistException.md#-)
- Add the role "ROLE_TODOER" to the Todoer entity

### Authorization

No authorization needed

```Java
TodoerDTO createTodoer(TodoerDTO todoerDTO)
```

## Delete Todoer

Given an id, delete the corresponding Todoer

### Logic

- If the calling user's ROLE is ROLE_ADMIN
  - check if the todoerId exist in the DB
    - if not, throw an exception [TodoerNotFoundException](./Exceptions/TodoerNotFoundException.md#-)
  - remove the user from the DB
- If the calling user's ROLE is ROLE_TODOER
  - check if the calling user's id is equal to the id given as parameter
    - if not, throw an exception [AccessDeniedException](./Exceptions/AccessDeniedException.md#-)
  - remove the user from the DB

### Authorization

Admin: ROLE_ADMIN

Todoer: ROLE_TODOER

```java
TodoerDTO deleteTodoer(Long todoerId)
```

## Get all Todoers

Return the list of all todoers 

### Logic

- Return the list of all todoers 

### Authorization

Admin: ROLE_ADMIN

```java
List<TodoerDTO> getAllTodoers()
```

## Get one Todoer

Given an id, return the corresponding Todoer

### Logic

- If calling user's ROLE is ROLE_ADMIN
  - Check if todoerId exist in the DB
    - if not, throw [TodoerNotFoundException](./Exceptions/TodoerNotFoundException.md#-)
  - Return the corresponding Todoer
- If calling user's ROLE is ROLE_TODOER
  - Check if the corresponding todoerId is equal to the calling todoer's id
    - if not, throw [AccessDeniedException](./Exceptions/AccessDeniedException.md#-)
  - Return the corresponding Todoer

### Authorization

Admin: ROLE_ADMIN

Todoer: ROLE_TODOER

```java
TodoerDTO getTodoer(Long todoerId)
```



## Get Todoer's Todos

Given a todoer's id, return all the corresponding Todos associated with that id

### Logic

- If ROLE is ROLE_ADMIN
  - Check if the todoerId exist in the DB
    - if not, throw [TodoerNotFoundException](./Exceptions/TodoerNotFoundException.md#-)
  - Return the Todos of the user todoerId
- If ROLE is ROLE_TODOER
  - Check if the todoerId is equal to the calling todoer's id
    - if not, throw [AccessDeniedException](./Exceptions/AccessDeniedException.md#-)
  - Return the Todos of the user todoerId

### Authorization

Admin: ROLE_ADMIN

Todoer: ROLE_TODOER

```java
List<TodoDTO> getTodos(Long todoerId)
```

