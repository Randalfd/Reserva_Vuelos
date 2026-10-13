package com.gutierrez.reserva_vuelos.backend.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
  String generateToken(UserDetails userDetails);
  String extractUsername(String token);
  boolean isValid(String username, UserDetails userDetails);
}
