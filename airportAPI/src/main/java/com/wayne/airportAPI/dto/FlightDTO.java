package com.wayne.airportAPI.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDTO {

    private Long id;

    @NotBlank(message = "Flight number is required")
    private String flightNumber;

    @NotNull(message = "Departure time is required")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Aircraft ID is required")
    private Long aircraftId;

    @NotNull(message = "Origin airport ID is required")
    private Long originAirportId;

    @NotNull(message = "Destination airport ID is required")
    private Long destinationAirportId;

    @NotNull(message = "Airline ID is required")
    private Long airlineId;

    @NotNull(message = "Gate ID is required")
    private Long gateId;

    private Set<Long> passengerIds;
}
