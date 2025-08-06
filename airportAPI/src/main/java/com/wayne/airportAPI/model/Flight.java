package com.wayne.airportAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight {

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

    public Flight() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public Aircraft getAircraft() { return aircraft; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }

    public Airport getOriginAirport() { return originAirport; }
    public void setOriginAirport(Airport originAirport) { this.originAirport = originAirport; }

    public Airport getDestinationAirport() { return destinationAirport; }
    public void setDestinationAirport(Airport destinationAirport) { this.destinationAirport = destinationAirport; }

    public Airline getAirline() { return airline; }
    public void setAirline(Airline airline) { this.airline = airline; }

    public Gate getGate() { return gate; }
    public void setGate(Gate gate) { this.gate = gate; }

    public Set<Passenger> getPassengers() { return passengers; }
    public void setPassengers(Set<Passenger> passengers) { this.passengers = passengers; }
}
