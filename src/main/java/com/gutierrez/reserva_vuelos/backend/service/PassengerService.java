package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundException;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerCreateRequestDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerResponseDto;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerUpdateRequestDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PassengerService {
    List<PassengerResponseDto> findAllPassenger();
    PassengerResponseDto findPassengerById(Long id) throws ResourceNotFoundException;
    PassengerResponseDto savePassenger(PassengerCreateRequestDto dto);

    @Transactional
    PassengerResponseDto updatePassenger(PassengerUpdateRequestDto dto, Long id);

    void removePassenger(Long id) throws ResourceNotFoundException;
    List<PassengerResponseDto> findByFirstnameLike(String pattern);
}
