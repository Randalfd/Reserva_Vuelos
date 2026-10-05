package com.gutierrez.reserva_vuelos.backend.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.gutierrez.reserva_vuelos.backend.model.dto.UserRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.UserResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserResponseDto userToUserResponseDto(User user);

  User userDtoToUser(UserRequestDto userRequestDto);
}
