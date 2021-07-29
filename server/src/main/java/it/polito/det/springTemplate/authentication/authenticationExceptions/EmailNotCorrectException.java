package it.polito.det.springTemplate.authentication.authenticationExceptions;

import javax.naming.AuthenticationException;

public class EmailNotCorrectException extends AuthenticationException {
    public EmailNotCorrectException(String msg){super(msg);}
}
