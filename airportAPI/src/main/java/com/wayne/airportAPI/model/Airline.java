package com.wayne.airportAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Airline name is required")
    private String name;

    @NotBlank(message = "Airline code is required")
    @Column(unique = true)
    private String code;

    @NotBlank(message = "Country is required")
    private String country;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
    private Set<Flight> flights;
}
