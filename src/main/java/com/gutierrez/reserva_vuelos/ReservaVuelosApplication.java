package com.gutierrez.reserva_vuelos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ReservaVuelosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservaVuelosApplication.class, args);
	}

}
