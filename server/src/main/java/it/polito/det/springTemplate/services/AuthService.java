package it.polito.det.springTemplate.services;

import it.polito.det.springTemplate.authentication.AuthenticationRequest;
import it.polito.det.springTemplate.authentication.SignUpRequest;
import it.polito.det.springTemplate.dtos.UserDTO;
import it.polito.det.springTemplate.jwt.JWTRefreshToken;
import it.polito.det.springTemplate.jwt.JWTToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AuthService {
    UserDTO register(SignUpRequest data);

    Map<String, String> login(AuthenticationRequest data);

    JWTToken refresh(JWTRefreshToken refreshToken);

    void logout(HttpServletRequest request, JWTRefreshToken refreshToken);
}
