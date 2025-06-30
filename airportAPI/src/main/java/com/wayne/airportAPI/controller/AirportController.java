package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/airports")
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @PostMapping
    public Airport createAirport(@Valid @RequestBody Airport airport) {
        return airportService.createAirport(airport);
    }

    @GetMapping("/{id}")
    public Airport getAirport(@PathVariable Long id) {
        return airportService.getAirportById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

    @GetMapping("/{id}/passengers")
    public List<Passenger> getPassengersByAirport(@PathVariable Long id) {
        return airportService.getPassengersByAirportId(id);
    }
}
