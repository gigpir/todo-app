package it.polito.det.springTemplate.services.userExceptions;

public class UsernameAlreadyExistException extends RuntimeException {
    public UsernameAlreadyExistException(String msg) { super(msg); };
}
