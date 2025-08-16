package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.GateDTO;
import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.model.Gate;
import com.wayne.airportAPI.repository.AirportRepository;
import com.wayne.airportAPI.repository.GateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GateService {

    private final GateRepository gateRepo;
    private final AirportRepository airportRepo;

    public List<GateDTO> getAllGates() {
        return gateRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<GateDTO> getGateById(Long id) {
        return gateRepo.findById(id)
                .map(this::toDTO);
    }

    public GateDTO createGate(Gate gate) {
        Gate saved = gateRepo.save(gate);
        return toDTO(saved);
    }

    public Optional<GateDTO> updateGate(Long id, Gate updatedGate) {
        return gateRepo.findById(id).map(existing -> {
            existing.setGateNumber(updatedGate.getGateNumber());
            existing.setAirport(updatedGate.getAirport());
            return toDTO(gateRepo.save(existing));
        });
    }

    public boolean deleteGate(Long id) {
        if (gateRepo.existsById(id)) {
            gateRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Gate fromDTO(GateDTO dto) {
        Gate gate = new Gate();
        gate.setId(dto.getId());
        gate.setGateNumber(dto.getGateNumber());

        Airport airport = airportRepo.findByCode(dto.getAirportCode())
                .orElseThrow(() -> new RuntimeException("Airport not found with code: " + dto.getAirportCode()));

        gate.setAirport(airport);
        return gate;
    }

    private GateDTO toDTO(Gate gate) {
        return GateDTO.builder()
                .id(gate.getId())
                .gateNumber(gate.getGateNumber())
                .airportName(gate.getAirport().getName())
                .airportCode(gate.getAirport().getCode())
                .build();
    }
}
