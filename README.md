# Reserva Vuelos App

Aplicación backend para la reserva de vuelos construida con **Java 21** y **Spring Boot 4.1**.

## Como levantar la aplicación

La aplicación usa **PostgreSQL** como base de datos. La configuración se inyecta por
variables de entorno (ver `src/main/resources/application.properties`):

| Variable            | Default                                  | Descripción                  |
| ------------------- | ---------------------------------------- | ---------------------------- |
| `DATABASE_URL`      | `jdbc:postgresql://localhost:5432/postgres` | JDBC URL de PostgreSQL   |
| `DATABASE_USER`     | `postgres`                               | Usuario de la base de datos  |
| `DATABASE_PASSWORD` | `reserva_vuelos_321`                     | Password de la base de datos |

Las tres tienen valor por defecto (sintaxis `${VARIABLE:default}`), así que la aplicación
arranca sin definir nada. Solo debes exportarlas cuando quieras apuntar a otra base de
datos. Docker Compose ya las define para que el contenedor use el host `psql` en lugar de
`localhost`.

### Opción 1: Docker Compose (recomendada)

Levanta la API y PostgreSQL 18 juntos, con la base de datos ya configurada.

#### Requisitos previos

- **Docker** y **Docker Compose** v2.

#### Compilar y levantar

Desde la raíz del proyecto:

```bash
docker compose up --build
```

> [!NOTE]
> El `Dockerfile` es multi-stage: la primera etapa compila el proyecto con Maven dentro
> de la imagen y la segunda solo copia el jar resultante. No hace falta tener JDK ni
> Maven instalados en tu máquina, ni compilar antes a mano.

La aplicación quedará disponible en:

- **URL base:** `http://localhost:8080`
- **PostgreSQL:** `localhost:5432` (usuario `postgres`, password `reserva_vuelos_321`)

Para detener los servicios:

```bash
docker compose down
```

### Opción 2: Sin Docker Compose (Maven)

Ejecutas la API con Maven, pero la base de datos sigue siendo PostgreSQL: tienes que
tenerla instalada y corriendo por tu cuenta.

### Opción 3: Intellij
Cargar la raiz del proyecto y compilar desde la propia herramienta.
Requiere tener DB cargada para ejecutar con datos mencionados;

#### Requisitos previos

- **JDK 21** o superior (definido en `pom.xml`).
- **Maven 3.9+**.
- **PostgreSQL** accesible desde `localhost:5432` con usuario `postgres` y password
  `reserva_vuelos_321` (puedes cambiar la password, pero entonces actualiza
  `DATABASE_PASSWORD`).

#### Compilar y ejecutar

```bash
mvn spring-boot:run
```

> [!NOTE]
> Si el proyecto no inicia y hay problemas de dependencias ejecutar `mvn clean package`.

Para compilar el proyecto sin ejecutarlo:

```bash
mvn clean compile
```

Para levantar todo (API + base de datos) pero únicamente con Maven, un caso intermedio
útil es deijar PostgreSQL en Docker y solo la API fuera:

```bash
docker compose up -d psql
DATABASE_URL=jdbc:postgresql://localhost:5432/postgres \
DATABASE_USER=postgres \
DATABASE_PASSWORD=reserva_vuelos_321 \
mvn spring-boot:run
```

> [!NOTE]
> El esquema de la base de datos se crea/actualiza solo al iniciar la aplicación
> (`spring.jpa.hibernate.ddl-auto=update`).
> Desde backend/config se proveen los datos mediante un seeder(al ejecutar se cargan datos), no se provee un data.sql por estos motivos.

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

| Campo         | Tipo          | Descripción                                       |
|---------------|---------------|---------------------------------------------------|
| `id`          | Long          | Identificador único                               |
| `name`        | String        | Nombre (requerido)                                |
| `mainAirport` | Airport       | Aeropuerto principal (relación)                   |
| `airports` | List<Airport> | Lista de aeropuertos con el que trabaja(relación) |
| `email`       | String        | Email de contacto (requerido)                     |
| `phone`       | String        | Teléfono                                          |

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
| ----------- | ------------- |-----------------------------------------------------------|
| `id`        | Long          | Identificador único                                       |
| `passenger` | Passenger     | Pasajero que reserva (relación)                           |
| `flight`    | Flight        | Vuelo reservado (relación)                                |
| `status`    | BookingStatus | Estado: `PENDING`, `CONFIRMED`, `CANCELLED`, `RESERVED`   |
| `seatType`  | SeatType      | Tipo de asiento: `ECONOMIC`, `BUSINESS`, `FIRST`, `VIP` |
