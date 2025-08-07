package com.wayne.airportAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Gate number is required")
    @Column(name = "gate_number", nullable = false)
    private String gateNumber;

    @ManyToOne
    @JoinColumn(name = "airport_id", nullable = false)
    private Airport airport;

    @OneToMany(mappedBy = "gate", cascade = CascadeType.ALL)
    private Set<Flight> flights;
}
