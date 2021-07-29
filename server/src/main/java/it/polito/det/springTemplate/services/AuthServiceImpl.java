package it.polito.det.springTemplate.services;

import it.polito.det.springTemplate.authentication.SignUpRequest;
import it.polito.det.springTemplate.dtos.UserDTO;
import it.polito.det.springTemplate.entities.BannedRefreshToken;
import it.polito.det.springTemplate.entities.BannedToken;
import it.polito.det.springTemplate.entities.User;
import it.polito.det.springTemplate.jwt.JWTRefreshToken;
import it.polito.det.springTemplate.jwt.JWTToken;
import it.polito.det.springTemplate.jwt.jwtExceptions.InvalidJwtRefreshTokenAuthenticationException;
import it.polito.det.springTemplate.repositories.BannedRefreshTokenRepository;
import it.polito.det.springTemplate.repositories.BannedTokenRepository;
import it.polito.det.springTemplate.repositories.UserRepository;
import it.polito.det.springTemplate.authentication.AuthenticationRequest;
import it.polito.det.springTemplate.jwt.JwtTokenProvider;
import it.polito.det.springTemplate.services.userExceptions.EmailAlreadyUsedException;
import it.polito.det.springTemplate.services.userExceptions.UsernameAlreadyExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BannedTokenService bannedTokenService;

    @Autowired
    BannedRefreshTokenService bannedRefreshTokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserDTO register(SignUpRequest data) {
        // Data is valid, so let's focus on creating the user
        // Let's check that the username it's unique
        if(userRepository.existsByUsername(data.getUsername())) {
            // username already exists
            throw new UsernameAlreadyExistException("Username "+data.getUsername()+" already exist");
        }

        if(userRepository.existsByEmail(data.getEmail())) {
            // email already in use
            throw new EmailAlreadyUsedException("Email "+data.getEmail()+" already used");
        }

        // Create the user
        User user = new User();
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setEnabled(true);

        user = userRepository.save(user);

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    //Funzione per il login
    public Map<String, String> login(AuthenticationRequest data){
        try {
            String username = data.getUsername();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "non trovato")).getRoles());


            Map<String, String> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            if(data.getRemainConnected()) {
                String refreshToken = jwtTokenProvider.createRefreshToken(username);
                model.put("refreshToken", refreshToken);
            }

            return model;
        }catch (DisabledException e){
            //Profilo non abilitato
            throw new BadCredentialsException("Il profilo non è ancora abilitato");
        }
        catch (AuthenticationException e) {
            //Username e/o password non corretti
            throw new BadCredentialsException("Username e/o password non corretti");
        }
    }

    @Override
    public JWTToken refresh(JWTRefreshToken refreshToken) {
        // Validate JWT token
        String token = refreshToken.getRefreshToken();
        if(jwtTokenProvider.validateRefreshToken(token)) {
            String username = jwtTokenProvider.getUsername(token);
            String newAuthToken = jwtTokenProvider.createToken(username, this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "non trovato")).getRoles());

            return new JWTToken(newAuthToken);
        }
        else {
            throw new InvalidJwtRefreshTokenAuthenticationException("Provided JWT refresh token may be invalid or missing");
        }
    }

    @Override
    public void logout(HttpServletRequest request, JWTRefreshToken refreshToken) {
        String banToken = jwtTokenProvider.resolveToken(request);
        String banRefreshToken = refreshToken.getRefreshToken();

        bannedTokenService.banToken(banToken);
        bannedRefreshTokenService.banRefreshToken(banRefreshToken);
    }
}
