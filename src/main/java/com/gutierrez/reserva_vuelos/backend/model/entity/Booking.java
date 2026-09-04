package com.gutierrez.reserva_vuelos.backend.model.entity;

import com.gutierrez.reserva_vuelos.backend.model.enums.BookingStatus;
import com.gutierrez.reserva_vuelos.backend.model.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
