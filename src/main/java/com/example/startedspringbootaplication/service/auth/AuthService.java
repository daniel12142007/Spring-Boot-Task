package com.example.startedspringbootaplication.service.auth;

import com.example.startedspringbootaplication.jwtConfig.JwtUtils;
import com.example.startedspringbootaplication.dto.auth.AuthRequest;
import com.example.startedspringbootaplication.dto.auth.AuthResponse;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication authenticate;
        if (userRepository.findByEmail(authRequest.getEmail()).isPresent()) {
            try {
                authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                ));
                String generateToken = jwtUtils.generateToken(authenticate);
                System.out.println(generateToken);
                return new AuthResponse(authRequest.getEmail(), generateToken);
            } catch (RuntimeException e) {
                throw new RuntimeException("-------------------------------------------------------------password is not correct-------------------------------------------------------------");
            }
        } else {
            throw new RuntimeException("-------------------------------------------------------------email is not correct-------------------------------------------------------------");
        }
    }
}