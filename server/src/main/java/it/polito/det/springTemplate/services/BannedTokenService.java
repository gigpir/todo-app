package it.polito.det.springTemplate.services;

public interface BannedTokenService {
    void banToken(String token);

    boolean isTokenBanned(String token);
}
