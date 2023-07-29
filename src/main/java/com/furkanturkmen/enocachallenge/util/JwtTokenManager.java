package com.furkanturkmen.enocachallenge.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.furkanturkmen.enocachallenge.exception.EnocaException;
import com.furkanturkmen.enocachallenge.exception.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class JwtTokenManager {
    private final String passwordKey = "#luC}VB>IsC)*>&x**zMqIdD}Pct_%T3w>{9&Zl$tbXZwfF3J+p%iD~o]8-!^`;";
    private final Long exTime = 1000L * 60 * 2600;

    public Optional<String> createToken(String id) {
        String token = "";
        try {
            token = JWT.create().withAudience()
                    .withClaim("id", id)
                    .withIssuer("enoca")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + exTime))
                    .sign(Algorithm.HMAC512(passwordKey));
            return Optional.of(token);
        } catch (Exception exception) {
            throw new EnocaException(ErrorType.INTERNAL_SERVER_ERROR);
        }
    }
    public Optional<String> validToken(String token){
        try {
            Algorithm algorithm= Algorithm.HMAC512(passwordKey);
            JWTVerifier verifier= JWT.require(algorithm).withIssuer("enoca").build();
            DecodedJWT decodedJWT= verifier.verify(token);
            if (decodedJWT==null) return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asString());

        }catch (Exception e){
            throw new EnocaException(ErrorType.ID_NOT_FOUND);
        }

    }

}

