package it.polito.det.springTemplate.jwt;

import io.jsonwebtoken.*;
import it.polito.det.springTemplate.jwt.jwtExceptions.InvalidJwtAuthenticationException;
import it.polito.det.springTemplate.services.BannedRefreshTokenService;
import it.polito.det.springTemplate.services.BannedTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    private byte[] signingKey;
    @Value("${security.jwt.token.expire-length}")
    private long tokenValidityInMilliseconds;
    @Value("${security.jwt.refresh-token.expire-length}")
    private long refreshTokenValidityInMilliseconds;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    BannedTokenService bannedTokenService;

    @Autowired
    BannedRefreshTokenService bannedRefreshTokenService;

    @PostConstruct
    public void init() {
        signingKey = secretKey.getBytes(StandardCharsets.UTF_8);
    }

    public String createToken(String username, List<String> authorities) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("authorities", authorities); // It is a refresh token
        claims.put("type", "access"); // It is an access token

        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMilliseconds);
        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS512, signingKey)//
                .compact();
    }

    public String createRefreshToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("type", "refresh"); // It is an access token

        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInMilliseconds);
        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS512, signingKey)//
                .compact();
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("Authorization");
        if (bearerToken != null && !bearerToken.isBlank() && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length()); // Remove the "Bearer " part
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
            String type = claims.getBody().get("type", String.class);
            return !claims.getBody().getExpiration().before(new Date()) && ( type == null || type.isEmpty() || type.contentEquals("access" )) && !bannedTokenService.isTokenBanned(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("JWT Token non valido o scaduto");
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
            String type =claims.getBody().get("type", String.class);
            return !claims.getBody().getExpiration().before(new Date()) && type.contentEquals("refresh" ) && !bannedRefreshTokenService.isRefreshTokenBanned(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("JWT Refresh Token non valido o scaduto");
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Timestamp getExpirationTimestamp(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
            return new Timestamp(claims.getBody().getExpiration().getTime());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("JWT Token non valido o scaduto");
        }
    }
}
