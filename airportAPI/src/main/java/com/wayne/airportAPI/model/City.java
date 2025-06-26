package com.example.airportapi.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String state;
    private Long population;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<Airport> airports;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public Long getPopulation() { return population; }
    public void setPopulation(Long population) { this.population = population; }

    public Set<Airport> getAirports() { return airports; }
    public void setAirports(Set<Airport> airports) { this.airports = airports; }
}
