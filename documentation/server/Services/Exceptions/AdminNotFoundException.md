# AdminNotFoundException

This exception indicates that an Admin has not been found in the DB

```java
puclic class AdminNotFoundException extends RuntimeException {
  public AdminNotFoundException (String msg) {
    super(msg);
  }
}
```

