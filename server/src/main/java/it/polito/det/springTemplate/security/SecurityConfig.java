package it.polito.det.springTemplate.security;

import it.polito.det.springTemplate.jwt.JwtTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashMap;
import java.util.Map;
import it.polito.det.springTemplate.jwt.JwtTokenProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder defaults = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // Use Argon2id for password encoding
        String encodingId = "argon2";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new Argon2PasswordEncoder(16, 16, 2, 1 << 15, 2));

        DelegatingPasswordEncoder result = new DelegatingPasswordEncoder(encodingId, encoders);
        result.setDefaultPasswordEncoderForMatches(defaults);

        return result;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .httpBasic()
                    .disable()
                .formLogin()
                    .disable()
                .csrf()
                    .disable() // TODO: enable in production
                .logout()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .requestMatchers()
                    .mvcMatchers("/api/**", "/auth/logout") // Protect /api/**, /api/logout
                    .and()
                .addFilterBefore(new JwtTokenVerifier(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .anyRequest().authenticated();

        http
                .headers()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");
        //@formatter:on
    }
}
