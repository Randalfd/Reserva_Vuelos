package com.gutierrez.reserva_vuelos.backend.config;

import com.gutierrez.reserva_vuelos.backend.model.entity.*;
import com.gutierrez.reserva_vuelos.backend.model.enums.BookingStatus;
import com.gutierrez.reserva_vuelos.backend.model.enums.Role;
import com.gutierrez.reserva_vuelos.backend.model.enums.SeatType;
import com.gutierrez.reserva_vuelos.backend.respository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataBaseSeeder implements CommandLineRunner {

  private final AirportRepository airportRepository;
  private final AirlineRepository airlineRepository;
  private final UserRepository userRepository;
  private final PassengerRepository passengerRepository;
  private final FlightRepository flightRepository;
  private final BookingRepository bookingRepository;
  private final PasswordEncoder passwordEncoder;

  public DataBaseSeeder(
          AirportRepository airportRepository,
          AirlineRepository airlineRepository,
          UserRepository userRepository,
          PassengerRepository passengerRepository,
          FlightRepository flightRepository,
          BookingRepository bookingRepository,
          PasswordEncoder passwordEncoder
  ) {
    this.airportRepository = airportRepository;
    this.airlineRepository = airlineRepository;
    this.userRepository = userRepository;
    this.passengerRepository = passengerRepository;
    this.flightRepository = flightRepository;
    this.bookingRepository = bookingRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) {

    if (airportRepository.count() > 0) {
      return;
    }

    // =========================
    // AIRPORTS
    // =========================

    Airport ezeiza = new Airport();
    ezeiza.setName("Ministro Pistarini International Airport");
    ezeiza.setIcao("SAEZ");
    ezeiza.setAddress("Autopista Riccheri Km 33.5");
    ezeiza.setCity("Buenos Aires");

    Airport cordoba = new Airport();
    cordoba.setName("Ingeniero Aeronáutico Ambrosio Taravella Airport");
    cordoba.setIcao("SACO");
    cordoba.setAddress("Av. La Voz del Interior 8500");
    cordoba.setCity("Cordoba");

    Airport mendoza = new Airport();
    mendoza.setName("Governor Francisco Gabrielli International Airport");
    mendoza.setIcao("SAME");
    mendoza.setAddress("Acceso Norte s/n");
    mendoza.setCity("Mendoza");

    Airport bariloche = new Airport();
    bariloche.setName("San Carlos de Bariloche Airport");
    bariloche.setIcao("SAZS");
    bariloche.setAddress("Ruta Nacional 40 Km 20");
    bariloche.setCity("Bariloche");

    Airport salta = new Airport();
    salta.setName("Martin Miguel de Guemes International Airport");
    salta.setIcao("SASA");
    salta.setAddress("Av. Aeropuerto Internacional 1");
    salta.setCity("Salta");

    airportRepository.saveAll(List.of(
            ezeiza,
            cordoba,
            mendoza,
            bariloche,
            salta
    ));

    // =========================
    // AIRLINES
    // =========================

    Airline aerolineas = new Airline();
    aerolineas.setName("Aerolineas Argentinas");
    aerolineas.setMainAirport(ezeiza);
    aerolineas.setAirports(new HashSet<>(
            Set.of(ezeiza, cordoba, mendoza, bariloche, salta)
    ));
    aerolineas.setEmail("contacto@aerolineas.com");
    aerolineas.setPhone("+54 11 4130 4000");

    Airline latam = new Airline();
    latam.setName("LATAM Airlines");
    latam.setMainAirport(ezeiza);
    latam.setAirports(new HashSet<>(
            Set.of(ezeiza, cordoba, mendoza)
    ));
    latam.setEmail("contacto@latam.com");
    latam.setPhone("+54 11 5032 0180");

    Airline jetsmart = new Airline();
    jetsmart.setName("JetSMART");
    jetsmart.setMainAirport(cordoba);
    jetsmart.setAirports(new HashSet<>(
            Set.of(cordoba, mendoza, bariloche, salta)
    ));
    jetsmart.setEmail("contacto@jetsmart.com");
    jetsmart.setPhone("+54 11 2206 7799");

    Airline flybondi = new Airline();
    flybondi.setName("Flybondi");
    flybondi.setMainAirport(ezeiza);
    flybondi.setAirports(new HashSet<>(
            Set.of(ezeiza, cordoba, bariloche, salta)
    ));
    flybondi.setEmail("contacto@flybondi.com");
    flybondi.setPhone("+54 11 3987 2222");

    Airline sky = new Airline();
    sky.setName("SKY Airline");
    sky.setMainAirport(mendoza);
    sky.setAirports(new HashSet<>(
            Set.of(mendoza, cordoba, ezeiza)
    ));
    sky.setEmail("contacto@skyairline.com");
    sky.setPhone("+56 2 2356 0000");

    airlineRepository.saveAll(List.of(
            aerolineas,
            latam,
            jetsmart,
            flybondi,
            sky
    ));

    // =========================
    // USERS + PASSENGERS
    // =========================

    User juanUser = createUser("Juan", "Perez", "juan.perez@email.com");
    User mariaUser = createUser("Maria", "Gomez", "maria.gomez@email.com");
    User carlosUser = createUser("Carlos", "Rodriguez", "carlos.rodriguez@email.com");
    User anaUser = createUser("Ana", "Fernandez", "ana.fernandez@email.com");
    User lucasUser = createUser("Lucas", "Martinez", "lucas.martinez@email.com");
    User admin = createAdmin("Admin", "Admin", "admin.admin@email.com");

    userRepository.saveAll(List.of(
            juanUser,
            mariaUser,
            carlosUser,
            anaUser,
            lucasUser,
            admin
    ));

    Passenger juan   = createPassenger("Juan",   "Pérez",     "juan.perez@gmail.com",       28123456);
    Passenger maria  = createPassenger("María",  "González",  "maria.gonzalez@hotmail.com", 30987654);
    Passenger carlos = createPassenger("Carlos", "Rodríguez", "carlos.rodriguez@gmail.com", 22456789);
    Passenger ana    = createPassenger("Ana",    "Fernández", "ana.fernandez@outlook.com",  35111222);
    Passenger lucas  = createPassenger("Lucas",  "Martínez",  "lucas.martinez@gmail.com",   40333444);

    passengerRepository.saveAll(List.of(
            juan,
            maria,
            carlos,
            ana,
            lucas
    ));

    // =========================
    // FLIGHTS
    // =========================

    Flight flight1 = createFlight(
            ezeiza,
            cordoba,
            aerolineas,
            85000,
            LocalDate.of(2026, 10, 1),
            LocalDate.of(2026, 10, 1)
    );

    Flight flight2 = createFlight(
            cordoba,
            mendoza,
            jetsmart,
            62000,
            LocalDate.of(2026, 10, 3),
            LocalDate.of(2026, 10, 3)
    );

    Flight flight3 = createFlight(
            ezeiza,
            bariloche,
            flybondi,
            95000,
            LocalDate.of(2026, 10, 5),
            LocalDate.of(2026, 10, 5)
    );

    Flight flight4 = createFlight(
            mendoza,
            salta,
            sky,
            78000,
            LocalDate.of(2026, 10, 7),
            LocalDate.of(2026, 10, 7)
    );

    Flight flight5 = createFlight(
            cordoba,
            ezeiza,
            latam,
            88000,
            LocalDate.of(2026, 10, 10),
            LocalDate.of(2026, 10, 10)
    );

    Flight flight6 = createFlight(
            salta,
            cordoba,
            aerolineas,
            70000,
            LocalDate.of(2026, 10, 12),
            LocalDate.of(2026, 10, 12)
    );

    Flight flight7 = createFlight(
            bariloche,
            ezeiza,
            flybondi,
            102000,
            LocalDate.of(2026, 10, 15),
            LocalDate.of(2026, 10, 15)
    );

    Flight flight8 = createFlight(
            ezeiza,
            mendoza,
            latam,
            91000,
            LocalDate.of(2026, 10, 18),
            LocalDate.of(2026, 10, 18)
    );

    flightRepository.saveAll(List.of(
            flight1,
            flight2,
            flight3,
            flight4,
            flight5,
            flight6,
            flight7,
            flight8
    ));

    // =========================
    // BOOKINGS
    // =========================

    Booking booking1 = createBooking(
            juan,
            flight1,
            BookingStatus.CONFIRMED,
            SeatType.VIP
    );

    Booking booking2 = createBooking(
            maria,
            flight2,
            BookingStatus.CONFIRMED,
            SeatType.BUSINESS
    );

    Booking booking3 = createBooking(
            carlos,
            flight3,
            BookingStatus.PENDING,
            SeatType.ECONOMIC
    );

    Booking booking4 = createBooking(
            ana,
            flight4,
            BookingStatus.CONFIRMED,
            SeatType.FIRST
    );

    Booking booking5 = createBooking(
            lucas,
            flight5,
            BookingStatus.CANCELLED,
            SeatType.ECONOMIC
    );

    Booking booking6 = createBooking(
            juan,
            flight6,
            BookingStatus.CONFIRMED,
            SeatType.BUSINESS
    );

    Booking booking7 = createBooking(
            maria,
            flight7,
            BookingStatus.PENDING,
            SeatType.ECONOMIC
    );

    Booking booking8 = createBooking(
            carlos,
            flight8,
            BookingStatus.CONFIRMED,
            SeatType.ECONOMIC
    );

    bookingRepository.saveAll(List.of(
            booking1,
            booking2,
            booking3,
            booking4,
            booking5,
            booking6,
            booking7,
            booking8
    ));
  }

  private User createUser(String firstname, String lastname, String email) {
    User user = new User();

    user.setFirstname(firstname);
    user.setLastname(lastname);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode("password123"));
    user.setRole(Role.USER);

    return user;
  }
  private User createAdmin(String firstname, String lastname, String email) {
    User user = new User();

    user.setFirstname(firstname);
    user.setLastname(lastname);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode("password123"));
    user.setRole(Role.ADMIN);

    return user;
  }

    private Passenger createPassenger(String firstname, String lastname, String email, int dni) {
    Passenger passenger = new Passenger();
    passenger.setFirstname(firstname);
    passenger.setLastname(lastname);
    passenger.setEmail(email);
    passenger.setDni(dni);
    passenger.setPassword(passwordEncoder.encode("password123"));
    passenger.setRole(Role.USER);

    return passenger;
  }

  private Flight createFlight(
          Airport origin,
          Airport destination,
          Airline airline,
          double price,
          LocalDate departure,
          LocalDate arrival
  ) {
    Flight flight = new Flight();

    flight.setOriginAirport(origin);
    flight.setDestinationAirport(destination);
    flight.setAirline(airline);
    flight.setPrice(price);
    flight.setDeparture(departure);
    flight.setArrival(arrival);

    return flight;
  }

  private Booking createBooking(
          Passenger passenger,
          Flight flight,
          BookingStatus status,
          SeatType seatType
  ) {
    Booking booking = new Booking();

    booking.setPassenger(passenger);
    booking.setFlight(flight);
    booking.setStatus(status);
    booking.setSeatType(seatType);

    return booking;
  }
}
