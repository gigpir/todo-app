package it.polito.det.springTemplate.services;

public interface BannedRefreshTokenService {
    void banRefreshToken(String refreshToken);

    boolean isRefreshTokenBanned(String refreshToken);
}
