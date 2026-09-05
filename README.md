# Reserva Vuelos App

Aplicación backend para la reserva de vuelos construida con **Java 21** y **Spring Boot 4.1**.

## Como levantar el servidor con Maven

### Requisitos previos

- **JDK 21** o superior (definido en `pom.xml`).
- **Maven 3.9+**.
- Base de datos **H2** en memoria (no requiere instalación, se inicia con la aplicación).

### Compilar y ejecutar

Desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

> [!IMPORTANT]
> Si el proyecto no inicia y hay problemas de dependencias ejecutar `mvn cleain package`
> Para compilar el proyecto sin ejecutarlo:

```bash
mvn clean compile
```

La aplicación quedará disponible en:

- **URL base:** `http://localhost:8080`
- **Consola H2:** `http://localhost:8080/h2-console`

## Entidades

### Airport (Aeropuerto)

Representa un aeropuerto.

| Campo     | Tipo   | Descripción           |
| --------- | ------ | --------------------- |
| `id`      | Long   | Identificador único   |
| `name`    | String | Nombre del aeropuerto |
| `icao`    | String | Código ICAO           |
| `address` | String | Dirección             |
| `city`    | String | Ciudad                |

### Airline (Aerolínea)

Representa una aerolínea. La relación con `Airport`.

| Campo     | Tipo    | Descripción                     |
| --------- | ------- | ------------------------------- |
| `id`      | Long    | Identificador único             |
| `name`    | String  | Nombre (requerido)              |
| `airport` | Airport | Aeropuerto principal (relación) |
| `email`   | String  | Email de contacto (requerido)   |
| `phone`   | String  | Teléfono                        |

### Flight (Vuelo)

Representa un vuelo entre dos aeropuertos, operado por una aerolínea.

| Campo                | Tipo      | Descripción                      |
| -------------------- | --------- | -------------------------------- |
| `id`                 | Long      | Identificador único              |
| `originAirport`      | Airport   | Aeropuerto de origen (relación)  |
| `destinationAirport` | Airport   | Aeropuerto de destino (relación) |
| `price`              | double    | Precio del vuelo                 |
| `departure`          | LocalDate | Fecha de salida                  |
| `arrival`            | LocalDate | Fecha de llegada                 |
| `airline`            | Airline   | Aerolínea operadora (relación)   |

### Passenger (Pasajero)

Representa un pasajero.

| Campo       | Tipo   | Descripción         |
| ----------- | ------ | ------------------- |
| `id`        | Long   | Identificador único |
| `firstname` | String | Nombre              |
| `lastname`  | String | Apellido            |
| `email`     | String | Email               |

### Booking (Reserva)

Representa la reserva de un vuelo por parte de un pasajero.

| Campo       | Tipo          | Descripción                                               |
| ----------- | ------------- | --------------------------------------------------------- |
| `id`        | Long          | Identificador único                                       |
| `passenger` | Passenger     | Pasajero que reserva (relación)                           |
| `flight`    | Flight        | Vuelo reservado (relación)                                |
| `status`    | BookingStatus | Estado: `Pending`, `Confirmed`, `Cancelled`, `Reserved`   |
| `seatType`  | SeatType      | Tipo de asiento: `Economic`, `Business`, `Premium`, `Vip` |
