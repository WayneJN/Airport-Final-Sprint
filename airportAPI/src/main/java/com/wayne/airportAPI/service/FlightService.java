package com.wayne.airportAPI.service;

import com.wayne.airportAPI.model.Flight;
import com.wayne.airportAPI.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepo;

    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }

    public Flight createFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    public Flight getFlightById(Long id) {
        return flightRepo.findById(id).orElseThrow();
    }

    public void deleteFlight(Long id) {
        flightRepo.deleteById(id);
    }
}
