package com.gutierrez.reserva_vuelos.backend.service;

import com.gutierrez.reserva_vuelos.backend.exeption.ResourceNotFoundExeption;
import com.gutierrez.reserva_vuelos.backend.mapper.PassengerMapper;
import com.gutierrez.reserva_vuelos.backend.model.dto.PassengerDto;
import com.gutierrez.reserva_vuelos.backend.model.entity.Passenger;
import com.gutierrez.reserva_vuelos.backend.respository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PassengerServiceImpl implements PassengerService{

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    PassengerMapper passengerMapper;
    @Override
    public List<PassengerDto> findAllPassenger() {
        return passengerRepository.findAll().
                stream().
                map(passengerMapper::passengerTopassengerDto).
                toList();
    }

    @Override
    public PassengerDto findPassengerById(Long id) {
        return passengerRepository.findById(id).map(passengerMapper::passengerTopassengerDto).orElseThrow(() -> new ResourceNotFoundExeption("Passenger Not Found"));
    }

    @Override
    public PassengerDto savePassenger(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.passengerDtoToPassenger(passengerDto);
        return passengerMapper.passengerTopassengerDto(passengerRepository.save(passenger));
    }

    @Override
    public PassengerDto updatePassenger(PassengerDto passengerDto, Long id) {
        Passenger savedPassenger = passengerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Passenger Not Found"));
        Passenger passenger = passengerMapper.passengerDtoToPassenger(passengerDto);

        if(Objects.nonNull(passenger.getFirstname()) && !"".equalsIgnoreCase(passenger.getFirstname())) {
            savedPassenger.setFirstname(passenger.getFirstname());
        }

        if(Objects.nonNull(passenger.getLastname()) && !"".equalsIgnoreCase(passenger.getLastname())) {
            savedPassenger.setLastname(passenger.getLastname());
        }

        if(Objects.nonNull(passenger.getEmail()) && !"".equalsIgnoreCase(passenger.getEmail())) {
            savedPassenger.setEmail(passenger.getEmail());
        }

        return passengerMapper.passengerTopassengerDto(passengerRepository.save(savedPassenger));
    }

    @Override
    public void removePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}
