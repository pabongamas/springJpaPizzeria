package com.platzi.platzipizzeria.web.config;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static String SECRET_KEY="245Sl4y3r#";
    private static Algorithm ALGORITHM=Algorithm.HMAC256(SECRET_KEY);
    
    public String create(String username){
        return JWT.create()
        .withSubject(username)
        .withIssuer("jhon-pizza")
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis()+TimeUnit.DAYS.toMillis(15)))
        .sign(ALGORITHM);
    }
}
