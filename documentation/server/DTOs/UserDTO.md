# UserDTO

This DTO models an **User** entity with all the attributes that can be publicly exported.

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
    private Long id;
    
    @NotBlank(message = "First name is empty")
    private String firstName;

    @NotBlank(message = "Last name is empty")
    private String lastName;

    @NotBlank(message = "Username is empty")
    private String username;

    @Email(message = "Email is not valid")
    private String email;
}
```

