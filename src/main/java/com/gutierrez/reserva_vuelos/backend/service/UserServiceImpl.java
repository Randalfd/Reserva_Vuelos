package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.mapper.UserMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.UserResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.User;
import com.gutierrez.reserva_vuelos.backend.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(@NonNull String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
  }

  @Override
  public List<UserResponseDto> findAllUsers() {
    return userRepository.findAll()
            .stream()
            .map(userMapper::userToUserResponseDto)
            .toList();
  }

  @Override
  public UserResponseDto findUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return userMapper.userToUserResponseDto(user);
  }

  @Override
  public UserResponseDto findUserByEmail(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return userMapper.userToUserResponseDto(user);
  }
}
