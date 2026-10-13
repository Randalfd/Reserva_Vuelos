package com.gutierrez.reserva_vuelos.backend.config;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.service.JwtService;
import com.gutierrez.reserva_vuelos.backend.service.JwtServiceImp;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain) throws ServletException, IOException {
    authenticateIfPossible(request);
    filterChain.doFilter(request, response);

  }

  private void authenticateIfPossible(HttpServletRequest request) {
    final String authHeader = request.getHeader("Authorization");

    if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) return;

    final String jwt = authHeader.substring(7);

    try {
      final String username = jwtService.extractUsername(jwt);
      if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) return;

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (!jwtService.isValid(username, userDetails)) return;

      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(token);

    } catch (JwtException | IllegalArgumentException | ResourceNotFoundException e) {
      // jwt no valido
      // continua de largo a doFilter y es manejado por  Spring.
      // TODO:
      // [] log system
    }

  }

}
