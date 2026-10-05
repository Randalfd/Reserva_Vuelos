package com.gutierrez.reserva_vuelos.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {
  @Value("${JWT_SECRET}")
  private String secret;
  public String extractUsername(String jwt) {
    return null;
  }
  public Claims extractAllClaims(String jwt) {
    return Jwts.parser().
            setSigningKey(getSignInKey()).
            build().
            parseClaimsJws(jwt).
            getBody();
  }
  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
