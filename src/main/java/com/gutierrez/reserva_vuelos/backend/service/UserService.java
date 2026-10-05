package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.model.dto.UserRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.UserResponseDto;

import java.util.List;

public interface UserService {
  List<UserResponseDto> findAllUsers();
  UserResponseDto findUserById();
  UserResponseDto saveUser(UserRequestDto userRequestDto);
  UserResponseDto updateUser(UserRequestDto userRequestDto, Long id);
  void removeUser(Long id);
  UserResponseDto findByEmail();
}
