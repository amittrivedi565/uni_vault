package com.univault.auth_service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class jwt {
    private static final String SECRET_KEY = "your_secret_key";
    private static final long EXPIRATION_TIME = 3600_000;

    public String generate_token(String user) {
        return JWT.create()
                .withSubject(user)
                .withIssuer("univault")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static DecodedJWT validate_token(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("univault")
                .build();
        return verifier.verify(token);
    }
}
