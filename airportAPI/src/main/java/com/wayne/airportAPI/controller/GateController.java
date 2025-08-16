package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.dto.GateDTO;
import com.wayne.airportAPI.model.Gate;
import com.wayne.airportAPI.service.GateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gates")
@CrossOrigin
@RequiredArgsConstructor
public class GateController {

    private final GateService gateService;

    @GetMapping
    public ResponseEntity<List<GateDTO>> getAllGates() {
        return ResponseEntity.ok(gateService.getAllGates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GateDTO> getGateById(@PathVariable Long id) {
        return gateService.getGateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GateDTO> createGate(@Valid @RequestBody GateDTO dto) {
        Gate gate = gateService.fromDTO(dto);
        GateDTO created = gateService.createGate(gate);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GateDTO> updateGate(@PathVariable Long id, @Valid @RequestBody GateDTO dto) {
        Gate gate = gateService.fromDTO(dto);
        return gateService.updateGate(id, gate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGate(@PathVariable Long id) {
        return gateService.deleteGate(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
