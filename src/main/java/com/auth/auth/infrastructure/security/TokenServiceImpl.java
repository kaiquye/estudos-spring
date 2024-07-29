package com.auth.auth.infrastructure.security;

import com.auth.auth.infrastructure.security.interfaces.ITokenService;
import com.auth.auth.modules.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class TokenServiceImpl implements ITokenService {
    private String SECRET = "my-secret";
    private String ISSUER = "my-secret";

    public  TokenServiceImpl() {

    }

    @Override
    public String generate(User user) {
        Algorithm algorithm = Algorithm.HMAC256(this.SECRET);
        String token = JWT.create()
                .withIssuer("auth-api")
                .withSubject(user.getEmail())
                .withExpiresAt(genExpirationDate())
                .sign(algorithm);
        return token;
    }

    @Override
    public Optional<DecodedJWT> compare(String token) {
       try{
           Algorithm algorithm = Algorithm.HMAC256(this.SECRET);

           JWTVerifier verifier = JWT.require(algorithm)
                   .withIssuer(this.ISSUER)
                   .build();

           DecodedJWT decodedJWT= verifier.verify(token);
           return Optional.ofNullable(decodedJWT);
       }catch (JWTVerificationException exception) {
           return Optional.empty();
       }
    }


    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
