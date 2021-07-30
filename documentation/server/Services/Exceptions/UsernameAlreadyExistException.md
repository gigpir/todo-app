# UsernameAlreadyExistException

This exception indicates that the provided username is already used

```java
puclic class UsernameAlreadyExistException extends RuntimeException {
    public UsernameAlreadyExistException (String msg) {
        super(msg);
    }
}
```