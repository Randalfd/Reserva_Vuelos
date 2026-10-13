package com.gutierrez.reserva_vuelos.backend.controller;

import com.gutierrez.reserva_vuelos.backend.model.dto.AuthResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.LoginRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.RegisterRequestDto;
import com.gutierrez.reserva_vuelos.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public AuthResponseDto login(@Valid @RequestBody LoginRequestDto request) {
    return authService.login(request);
  }

  @PostMapping("/register")
  public AuthResponseDto register(@Valid @RequestBody RegisterRequestDto request) {
    return authService.register(request);
  }
}
