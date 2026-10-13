package com.gutierrez.reserva_vuelos.backend.mapper;

import com.gutierrez.reserva_vuelos.backend.model.dto.RegisterRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.UserRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.UserResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserResponseDto userToUserResponseDto(User user);

  User userDtoToUser(UserRequestDto userRequestDto);

  @Mapping(target = "role", constant = "USER")
  User userRegisterRequestToUser(RegisterRequestDto registerRequest);
}
