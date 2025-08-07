package com.wayne.airportAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight {

    @NotNull(message = "Flight number is required")
    @Column(name = "flight_number", unique = true)
    private String flightNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Departure time is required")
    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    @NotNull(message = "Aircraft is required")
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    @NotNull(message = "Origin airport is required")
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    @NotNull(message = "Destination airport is required")
    private Airport destinationAirport;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    @NotNull(message = "Airline is required")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "gate_id")
    @NotNull(message = "Gate is required")
    private Gate gate;

    @ManyToMany
    @JoinTable(
            name = "flight_passenger",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private Set<Passenger> passengers;
}
