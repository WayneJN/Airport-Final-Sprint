package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.GateDTO;
import com.wayne.airportAPI.model.Gate;
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

    private GateDTO toDTO(Gate gate) {
        return GateDTO.builder()
                .id(gate.getId())
                .gateNumber(gate.getGateNumber())
                .airportName(gate.getAirport() != null ? gate.getAirport().getName() : null)
                .airportCode(gate.getAirport() != null ? gate.getAirport().getCode() : null)
                .build();
    }
}
