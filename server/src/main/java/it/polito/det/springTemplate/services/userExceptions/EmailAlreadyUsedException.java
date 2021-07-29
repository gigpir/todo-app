package it.polito.det.springTemplate.services.userExceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String msg) { super(msg); };
}
