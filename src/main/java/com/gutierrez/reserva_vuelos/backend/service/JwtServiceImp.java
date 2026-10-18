package com.gutierrez.reserva_vuelos.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtServiceImp implements JwtService{
  // 15 minutes to expire token
  private static final Duration TOKEN_EXPIRE = Duration.ofMinutes(15);

  private final SecretKey signingKey;

  public JwtServiceImp(@Value("${jwt.secret}") String secret) {
    byte[] bytes = Decoders.BASE64.decode(secret);
    this.signingKey = Keys.hmacShaKeyFor(bytes);
  }

  @Override
  public String generateToken(UserDetails userDetails) {
    Date now = new Date();
    Date expiration = Date.from(now.toInstant().plus(TOKEN_EXPIRE));

    return Jwts.builder()
            .subject(userDetails.getUsername())
            .claim("roles", userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
            .issuedAt(now)
            .expiration(expiration)
            .signWith(signingKey)
            .compact();
  }

  @Override
  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }

  private Claims extractClaims(String token) {
    return Jwts.parser()
            .verifyWith(signingKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  private boolean isTokenExpired(String token) {
    return extractClaims(token).getExpiration().before(new Date());
  }

  @Override
  public boolean isValid(String username, UserDetails userDetails) {
    return username.equals(userDetails.getUsername());
  }
}