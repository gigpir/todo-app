# AdminService

Contains all the functions that operate on the **Admin** entity

## Create new Admin

### Logic

- It checks if the provided username is already used
  - if yes, it throws an exception <u>UsernameAlreadyExistException</u>
- It checks if the provided email is already used
  - if yes, it throws an exception <u>EmailAlreadyUsedException</u>
- It adds the role "ROLE_ADMIN" to the Admin entity

### Authorization

- Admin: ROLE_ADMIN

```java
AdminDTO createAdmin(AdminDTO adminDTO)
```



## Delete Admin

It takes care of deleting an existing admin

### Logic

- It checks that the admin exists
  - If they does not exist, it throws a <u>AdminNotFoundException</u>
- It removes the admin from the DB

### Authorization

- Admin: ROLE_ADMIN

```java
AdminDTO deleteAdmin(Long adminId)
```

## Get all Admins

It returns the list of all Admins

### Logic

- return the list of all Admins

### Authorization

Admin: ROLE_ADMIN

```java
List<AdminDTO> getAllAdmins()
```

## Get one Admin

It takes as input an id and it returns the corresponding Admin 

### Logic

- Check if Admin exist
  - if it doesn't exist, throw <u>AdminNotFoundException</u>
- Return the corresponding Admin

### Authorization

Admin: ROLE_ADMIN

```java
AdminDTO getAdmin(Long adminId)
```

