package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.mapper.PassengerMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerCreateRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerUpdateRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.model.enums.Role;
import com.gutierrez.reserva_vuelos.backend.respository.PassengerRepository;
import com.gutierrez.reserva_vuelos.backend.respository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

  private final PassengerRepository passengerRepository;
  private final PassengerMapper passengerMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public List<PassengerResponseDto> findAllPassenger() {
    return passengerRepository.findAll()
            .stream()
            .map(passengerMapper::toDto)
            .toList();
  }

  @Override
  public PassengerResponseDto findPassengerById(Long id) {
    return passengerRepository.findById(id)
            .map(passengerMapper::toDto)
            .orElseThrow(() -> new ResourceNotFoundException("Passenger Not Found"));
  }

  @Override
  @Transactional
  public PassengerResponseDto savePassenger(PassengerCreateRequestDto dto) {
    if(userRepository.existsByEmail(dto.email())) {
      throw new IllegalArgumentException("Email is Already In Use");
    }
    Passenger passenger = passengerMapper.passengerDtoToPassenger(dto);
    passenger.setPassword(passwordEncoder.encode(dto.password()));
    passenger.setRole(Role.USER);

    return passengerMapper.toDto(passengerRepository.save(passenger));
  }

  @Override
  @Transactional
  public PassengerResponseDto updatePassenger(PassengerUpdateRequestDto dto, Long id) {
    Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Passenger Not Found"));
    if(userRepository.existsByEmailAndIdNot(dto.email(),id)) {
      throw new IllegalArgumentException("Email is Already In Use");
    }

    passengerMapper.updateEntity(dto,passenger);
    return passengerMapper.toDto(passenger);
  }

  @Override
  @Transactional
  public void removePassenger(Long id) throws ResourceNotFoundException {
    Passenger passenger = passengerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Passenger Not Found"));
    passengerRepository.delete(passenger);
  }

  @Override
  public List<PassengerResponseDto> findByFirstnameLike(String pattern) {
    return passengerRepository.findByFirstnameLike(pattern)
            .stream()
            .map(passengerMapper::toDto)
            .toList();
  }
}
