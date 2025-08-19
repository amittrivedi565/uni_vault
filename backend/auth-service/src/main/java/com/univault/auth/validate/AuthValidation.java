package com.univault.auth.validate;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.univault.auth.config.JwtProperties;
import com.univault.auth.service.AuthServiceImpl;

@Service
public class AuthValidation {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final JwtProperties jwtProperties;

    public AuthValidation(JwtProperties jwtProperties){
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(String user) {
        return JWT.create()
            .withSubject(user)
            .withIssuer(jwtProperties.getIssuer())
            .withIssuedAt(new Date())
            .withExpiresAt(
                new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime())
            )
            .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
            JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(jwtProperties.getIssuer()) // always matches config
            .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
        logger.warn("Token validation failed: {}", ex.getMessage());
        return false;
        }   
    }
}
