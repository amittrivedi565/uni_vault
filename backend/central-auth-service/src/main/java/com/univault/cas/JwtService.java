package com.univault.cas;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final String SECRET_KEY = "your_secret_key";
    private static final long EXPIRATION_TIME = 3600_000;

    public String generateToken(String user) {
        return JWT.create()
            .withSubject(user)
            .withIssuer("univault")
            .withIssuedAt(new Date())
            .withExpiresAt(
                new Date(System.currentTimeMillis() + EXPIRATION_TIME)
            )
            .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static DecodedJWT validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("univault")
            .build();
        return verifier.verify(token);
    }
}
