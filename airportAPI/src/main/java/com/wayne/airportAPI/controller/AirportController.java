package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.dto.AirportDTO;
import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@CrossOrigin
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public List<AirportDTO> getAllAirports() {
        return airportService.getAllAirports().stream()
                .map(airportService::toDTO)
                .toList();
    }

    @PostMapping
    public AirportDTO createAirport(@Valid @RequestBody AirportDTO dto) {
        Airport airport = airportService.fromDTO(dto);
        Airport saved = airportService.createAirport(airport);
        return airportService.toDTO(saved);
    }

    @GetMapping("/{id}")
    public AirportDTO getAirport(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);
        return airportService.toDTO(airport);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

    @GetMapping("/{id}/passengers")
    public List<Passenger> getPassengersByAirport(@PathVariable Long id) {
        return airportService.getPassengersByAirportId(id);
    }

    @PutMapping("/{id}")
    public AirportDTO updateAirport(@PathVariable Long id, @Valid @RequestBody AirportDTO dto) {
        Airport airport = airportService.fromDTO(dto);
        Airport updated = airportService.updateAirport(id, airport);
        return airportService.toDTO(updated);
    }



}
