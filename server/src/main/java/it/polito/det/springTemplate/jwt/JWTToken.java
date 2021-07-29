package it.polito.det.springTemplate.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class JWTToken {
    @NotNull
    private String token;
}
