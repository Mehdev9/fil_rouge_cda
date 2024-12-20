package com.insy.fil_rouge_cda.security;


import com.insy.fil_rouge_cda.models.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {


    @Value("${application.security.jwt.secret}")
    private String secret;

    @Value("${application.security.jwt.expiration}")
    private long expiration;




    public String generateToken(Account user) {
        String role = user.getRole().name();
        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .claim("role", role)
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getuserNameFromToken(String token){
        Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public boolean isTokenExpired(String token){
        Date claimExpiration = getAllClaimsFromToken(token).getExpiration();
        return claimExpiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails user){
        boolean isTokenExpired = isTokenExpired(token);
        String usernameFromClaims = getuserNameFromToken(token);
        boolean isUsernameValid = usernameFromClaims.equals(user.getUsername());

        return isUsernameValid && !isTokenExpired;
    }

}
