package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.mapper.UserMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.AuthResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.LoginRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.RegisterRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.User;
import com.gutierrez.reserva_vuelos.backend.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authManager;
  private final UserDetailsService userDetailsService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private UserMapper userMapper;

  public AuthResponseDto login(LoginRequestDto request) {
    authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
            )
    );

    UserDetails userDetails =
            userDetailsService.loadUserByUsername(request.email());

    String token = jwtService.generateToken(userDetails);

    return new AuthResponseDto(token, userDetails.getUsername(), userDetails.getAuthorities().iterator().next().getAuthority()
    );
  }

  public AuthResponseDto register(RegisterRequestDto request) {
    if (userRepository.findByEmail(request.email()).isPresent()) {
      throw new IllegalArgumentException("Email Alredy Registered");
    }
    User user = userMapper.userRegisterRequestToUser(request);
    userRepository.save(user);

    UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());

    String token = jwtService.generateToken(userDetails);

    return new AuthResponseDto(token, user.getEmail(), user.getRole().toString());
  }
}
