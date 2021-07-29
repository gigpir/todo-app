package it.polito.det.springTemplate.controllers;

import it.polito.det.springTemplate.authentication.AuthenticationRequest;
import it.polito.det.springTemplate.authentication.SignUpRequest;
import it.polito.det.springTemplate.dtos.UserDTO;
import it.polito.det.springTemplate.jwt.JWTRefreshToken;
import it.polito.det.springTemplate.jwt.JWTToken;
import it.polito.det.springTemplate.services.AuthService;
import it.polito.det.springTemplate.services.userExceptions.EmailAlreadyUsedException;
import it.polito.det.springTemplate.services.userExceptions.UsernameAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    //End point per il login
    public UserDTO register(@RequestBody @Valid SignUpRequest data){
        try {
            return authService.register(data);
        }
        catch (UsernameAlreadyExistException | EmailAlreadyUsedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/login")
    //End point per il login
    public Map<String, String> login(@RequestBody @Valid AuthenticationRequest data){
        try{
            return authService.login(data);
        }
        catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/refreshToken")
    public JWTToken token(@RequestBody @Valid JWTRefreshToken refreshToken) {
        try {
            return authService.refresh(refreshToken);
        }
        catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, @RequestBody @Valid JWTRefreshToken refreshToken) {
        try {
            authService.logout(request, refreshToken);
        }
        catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
