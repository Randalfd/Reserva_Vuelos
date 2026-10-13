package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.dto.UserRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
  List<UserResponseDto> findAllUsers();
  UserResponseDto findUserById();
}
