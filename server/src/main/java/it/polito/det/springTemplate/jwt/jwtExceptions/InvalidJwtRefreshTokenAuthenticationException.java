package it.polito.det.springTemplate.jwt.jwtExceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtRefreshTokenAuthenticationException extends AuthenticationException {
    public InvalidJwtRefreshTokenAuthenticationException(String e) {
        super(e);
    }
}
