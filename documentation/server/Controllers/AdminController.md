# Admin Controller

Handle all requests to endpoint **/api/v1/admin**

```java
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController{
  ...
}
```

## Get All Admins

Return the list of admins.

It handles the **GET** request to the endpoint **/** [REST API documentation](../REST API/api/v1/admin/admin.md#-)

### Logic

Call the method `getAllAdmins()` of the [AdminService](../Services/AdminService.md#get-all-admins).

```java
@GetMapping("/")
public List<AdminDTO> getAllAdmins()
```

## Create new admin

Create a new admin.

Handle the **POST** request  to the endpoint **/** [REST API documentation](../REST API/api/v1/admin/admin.md#-)

### Logic

Call the method `createAdmin()` of the [AdminService](../Services/AdminService.md#create-new-admin).

Map the following exceptions to the corresponding HTTP status codes:

* [UsernameAlreadyExistException](../Services/Exceptions/UsernameAlreadyExistException.md#-), [EmailAlreadyUsedException](,,/Services/Exceptions/EmailAlreadyUsedException.md#-) -> **409**

```java
@PostMapping("/")
public AdminDTO createAdmin(@Valid @RequestBody AdminDTO adminDTO)
```



## Get one admin

Return the details about one admin

Handle the **GET** request to the endpoint **/{adminId}** [REST API documentation](../REST API/api/v1/admin/admin.md#--adminid-)

### Logic

Call the method `getAdmin()` of the [AdminService](../Services/AdminService.md#get-one-admin).

Map the following exceptions to the corresponding HTTP status codes:

* [AdminNotFoundException](,,/Services/Exceptions/AdminNotFoundException.md#-) -> **404**

```java
@GetMapping("/{adminId}")
public AdminDTO getAdmin(@PathVariable ("adminId") Long adminId)
```



## Delete admin

Delete one admin 

Handle the **DELETE** request to the endpoint **/{adminId}** [REST API documentation](../REST API/api/v1/admin/admin.md#--adminid-)

### Logic

Call the method `deleteAdmin()` of the [AdminService](../Services/AdminService.md#delete-admin).

Map the following exceptions to the corresponding HTTP status codes:

* [AdminNotFoundException](,,/Services/Exceptions/AdminNotFoundException.md#-) -> **404**

```java
@DeleteMapping("/{adminId}")
public AdminDTO deleteAdmin(@PathVariable ("adminId") Long adminId)
```