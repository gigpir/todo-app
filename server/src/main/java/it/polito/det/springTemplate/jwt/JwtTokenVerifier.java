package it.polito.det.springTemplate.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.det.springTemplate.jwt.jwtExceptions.InvalidJwtAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class JwtTokenVerifier extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenVerifier(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = jwtTokenProvider.resolveToken(httpServletRequest);
            if(token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            else {
                throw new InvalidJwtAuthenticationException("JWT Token non valido o mancante");
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
        catch (AuthenticationException e) {
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            httpServletResponse.setStatus(status.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", ZonedDateTime.now( ZoneOffset.UTC ).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxx")));
            body.put("status", status.value());

            body.put("error", status.getReasonPhrase());
            body.put("message", e.getMessage());
            body.put("path", httpServletRequest.getRequestURI());

            ObjectMapper obj = new ObjectMapper();
            obj.writeValue(httpServletResponse.getWriter(), body);
        }
    }
}
