package it.polito.det.springTemplate.authentication.authenticationExceptions;

import javax.naming.AuthenticationException;

public class PasswordMustBeEqualsException extends AuthenticationException {
    public PasswordMustBeEqualsException(String msg){super(msg);}
}
