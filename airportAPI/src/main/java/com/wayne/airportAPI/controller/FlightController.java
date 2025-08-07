package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.dto.FlightDTO;
import com.wayne.airportAPI.dto.FlightResponseDTO;
import com.wayne.airportAPI.model.Flight;
import com.wayne.airportAPI.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public List<FlightResponseDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public FlightResponseDTO getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    @PostMapping
    public FlightResponseDTO createFlight(@Valid @RequestBody FlightDTO flightDTO) {
        Flight flight = flightService.createFlightFromDTO(flightDTO);
        return flightService.toResponseDTO(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    @GetMapping("/search")
    public List<FlightResponseDTO> getFlightsBetweenAirports(@RequestParam Long originId,
                                                             @RequestParam Long destinationId) {
        return flightService.getFlightsBetweenAirports(originId, destinationId).stream()
                .map(flightService::toResponseDTO)
                .toList();
    }
}
