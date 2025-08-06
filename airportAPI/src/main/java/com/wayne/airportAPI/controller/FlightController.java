package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.model.Flight;
import com.wayne.airportAPI.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

    @GetMapping("/search")
    public List<Flight> getFlightsBetweenAirports(@RequestParam Long originId,
                                                  @RequestParam Long destinationId) {
        return flightService.getFlightsBetweenAirports(originId, destinationId);
    }

    @PostMapping
    public FlightDTO createFlight(@Valid @RequestBody FlightDTO flightDTO) {
        Flight flight = flightService.createFlightFromDTO(flightDTO);
        return flightService.toDTO(flight);
    }

    @GetMapping("/{id}")
    public FlightDTO getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return flightService.toDTO(flight);
    }

}
