package it.polito.det.springTemplate.services;

import it.polito.det.springTemplate.entities.BannedToken;
import it.polito.det.springTemplate.jwt.JwtTokenProvider;
import it.polito.det.springTemplate.repositories.BannedTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class BannedTokenServiceImpl implements BannedTokenService {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    BannedTokenRepository bannedTokenRepository;

    @Override
    public void banToken(String token) {
        try {
            if (jwtTokenProvider.validateToken(token)) {
                BannedToken bannedToken = new BannedToken(token, jwtTokenProvider.getExpirationTimestamp(token));
                bannedTokenRepository.save(bannedToken);
            }
        }
        catch (AuthenticationException ignored) {
            // Ignore exception
        }
    }

    @Override
    public boolean isTokenBanned(String token) {
        return bannedTokenRepository.findById(token).isPresent();
    }

    @Scheduled(fixedRate = 3600000, initialDelay = 0)
    //Funzione periodica che elimina tutti i token scaduti
    public void checkForExpiredTokens() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // Lista dei token scaduti
        List<BannedToken> tokens = bannedTokenRepository.findAllByExpiryDateBefore(now);

        // Elimino tutti i token scaduti
        bannedTokenRepository.deleteAll(tokens);
    }
}
