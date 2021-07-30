# TodoNotFoundException

This exception indicates that a Todo has not been found in the DB

```java
puclic class TodoNotFoundException extends RuntimeException {
  public TodoNotFoundException (String msg) {
    super(msg);
  }
}
```

