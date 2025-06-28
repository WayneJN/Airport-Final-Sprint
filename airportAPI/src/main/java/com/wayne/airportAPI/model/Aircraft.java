package com.wayne.airportAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String airlineName;
    private Integer numberOfPassengers;

    @ManyToMany
    @JoinTable(
        name = "aircraft_passenger",
        joinColumns = @JoinColumn(name = "aircraft_id"),
        inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private Set<Passenger> passengers;

    @ManyToMany
    @JoinTable(
        name = "aircraft_airport",
        joinColumns = @JoinColumn(name = "aircraft_id"),
        inverseJoinColumns = @JoinColumn(name = "airport_id")
    )
    private Set<Airport> airports;
}
