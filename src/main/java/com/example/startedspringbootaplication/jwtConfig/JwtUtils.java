package com.example.startedspringbootaplication.jwtConfig;

import com.example.startedspringbootaplication.model.user.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class JwtUtils {
    private final JwtConfig jwtConfig;

    public String generateToken(Authentication authentication) {
        Users authInfo = (Users) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(authInfo.getEmail())
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecretKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (jwtConfig.getExpirationDateAfterDays() * 6500000)))
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean verifyToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtConfig.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}