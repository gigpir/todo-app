# TodoDTO

This DTO models an **Todo** entity with all the attributes that can be publicly exported.

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
  private Long id;
  
  @NotBlank(message = "Title is empty")
  private String title;
  
  @NotBlank(message = "description is empty")
  private String description;
  
  private Boolean done;
```

