package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.dto.AirlineDTO;
import com.wayne.airportAPI.model.Airline;
import com.wayne.airportAPI.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@CrossOrigin
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;

    @GetMapping
    public ResponseEntity<List<AirlineDTO>> getAllAirlines() {
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineDTO> getAirlineById(@PathVariable Long id) {
        return airlineService.getAirlineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AirlineDTO> createAirline(@Valid @RequestBody AirlineDTO dto) {
        Airline airline = airlineService.fromDTO(dto);
        AirlineDTO created = airlineService.createAirline(airline);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineDTO> updateAirline(@PathVariable Long id, @Valid @RequestBody AirlineDTO dto) {
        Airline airline = airlineService.fromDTO(dto);
        return airlineService.updateAirline(id, airline)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirline(@PathVariable Long id) {
        return airlineService.deleteAirline(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
