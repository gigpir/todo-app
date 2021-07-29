package it.polito.det.springTemplate.services;

import it.polito.det.springTemplate.entities.BannedRefreshToken;
import it.polito.det.springTemplate.jwt.JwtTokenProvider;
import it.polito.det.springTemplate.repositories.BannedRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class BannedRefreshTokenServiceImpl implements BannedRefreshTokenService {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    BannedRefreshTokenRepository bannedRefreshTokenRepository;

    @Override
    public void banRefreshToken(String refreshToken) {
        try {
            if(jwtTokenProvider.validateRefreshToken(refreshToken)) {
                BannedRefreshToken bannedRefreshToken = new BannedRefreshToken(refreshToken, jwtTokenProvider.getExpirationTimestamp(refreshToken));
                bannedRefreshTokenRepository.save(bannedRefreshToken);
            }
        }
        catch (AuthenticationException ignored) {
            // We ignore the exception
        }
    }

    @Override
    public boolean isRefreshTokenBanned(String refreshToken) {
        return bannedRefreshTokenRepository.findById(refreshToken).isPresent();
    }

    @Scheduled(fixedRate = 3600000, initialDelay = 0)
    //Funzione periodica che elimina tutti i token scaduti
    public void checkForExpiredTokens() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // Lista dei token scaduti
        List<BannedRefreshToken> tokens = bannedRefreshTokenRepository.findAllByExpiryDateBefore(now);

        // Elimino tutti i token scaduti
        bannedRefreshTokenRepository.deleteAll(tokens);
    }
}
