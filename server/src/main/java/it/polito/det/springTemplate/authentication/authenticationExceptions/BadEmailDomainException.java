package it.polito.det.springTemplate.authentication.authenticationExceptions;

import javax.naming.AuthenticationException;

public class BadEmailDomainException extends AuthenticationException {
    public BadEmailDomainException(String msg){super(msg);}
}
