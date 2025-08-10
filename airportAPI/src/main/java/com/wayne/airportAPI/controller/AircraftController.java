package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.dto.AircraftDTO;
import com.wayne.airportAPI.model.Aircraft;
import com.wayne.airportAPI.service.AircraftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
@CrossOrigin
@RequiredArgsConstructor
public class AircraftController {

    private final AircraftService aircraftService;

    @GetMapping
    public List<AircraftDTO> getAllAircraft() {
        return aircraftService.getAllAircraft().stream()
                .map(aircraftService::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public AircraftDTO getAircraftById(@PathVariable Long id) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        return aircraftService.toDTO(aircraft);
    }

    @PostMapping
    public AircraftDTO createAircraft(@Valid @RequestBody AircraftDTO dto) {
        Aircraft aircraft = aircraftService.fromDTO(dto);
        Aircraft saved = aircraftService.createAircraft(aircraft);
        return aircraftService.toDTO(saved);
    }

    @PutMapping("/{id}")
    public AircraftDTO updateAircraft(@PathVariable Long id, @Valid @RequestBody AircraftDTO dto) {
        Aircraft aircraft = aircraftService.fromDTO(dto);
        Aircraft updated = aircraftService.updateAircraft(id, aircraft);
        return aircraftService.toDTO(updated);
    }


    @DeleteMapping("/{id}")
    public void deleteAircraft(@PathVariable Long id) {
        aircraftService.deleteAircraft(id);
    }
}
