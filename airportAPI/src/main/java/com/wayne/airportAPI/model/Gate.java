package com.wayne.airportAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Gate number is required")
    @Column(name = "gate_number")
    private String gateNumber;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @OneToMany(mappedBy = "gate", cascade = CascadeType.ALL)
    private Set<Flight> flights;
}
