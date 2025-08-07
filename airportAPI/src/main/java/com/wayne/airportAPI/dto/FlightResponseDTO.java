package com.wayne.airportAPI.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class FlightResponseDTO {
    private Long id;
    private String flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String aircraftModel;
    private String originAirportCode;
    private String destinationAirportCode;
    private String airlineName;
    private String gateNumber;
    private Set<Long> passengerIds;
}
