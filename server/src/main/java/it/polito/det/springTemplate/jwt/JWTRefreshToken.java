package it.polito.det.springTemplate.jwt;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JWTRefreshToken {
    @NotNull
    private String refreshToken;
}
