package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.UserResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.User;
import com.gutierrez.reserva_vuelos.backend.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  @Autowired
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(@NonNull String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return user;
  }

  @Override
  public List<UserResponseDto> findAllUsers() {
    return List.of();
  }

  @Override
  public UserResponseDto findUserById() {
    return null;
  }
}
