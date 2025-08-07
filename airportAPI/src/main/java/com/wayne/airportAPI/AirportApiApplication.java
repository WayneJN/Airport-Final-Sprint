package com.wayne.airportAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.wayne.airportAPI.model")
public class AirportApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AirportApiApplication.class, args);
	}
}

