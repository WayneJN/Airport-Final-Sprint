package com.wayne.airportAPI.service;

import com.wayne.airportAPI.model.Aircraft;
import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.model.Flight;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.repository.AircraftRepository;
import com.wayne.airportAPI.repository.AirportRepository;
import com.wayne.airportAPI.repository.FlightRepository;
import com.wayne.airportAPI.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private AircraftRepository aircraftRepo;

    @Autowired
    private AirportRepository airportRepo;

    @Autowired
    private PassengerRepository passengerRepo;

    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepo.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public Flight createFlight(Flight flight) {
        Aircraft aircraft = aircraftRepo.findById(flight.getAircraft().getId())
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        Airport origin = airportRepo.findById(flight.getOriginAirport().getId())
                .orElseThrow(() -> new RuntimeException("Origin airport not found"));

        Airport destination = airportRepo.findById(flight.getDestinationAirport().getId())
                .orElseThrow(() -> new RuntimeException("Destination airport not found"));

        Set<Passenger> passengers = flight.getPassengers().stream()
                .map(p -> passengerRepo.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Passenger not found")))
                .collect(Collectors.toSet());

        flight.setAircraft(aircraft);
        flight.setOriginAirport(origin);
        flight.setDestinationAirport(destination);
        flight.setPassengers(passengers);

        return flightRepo.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepo.deleteById(id);
    }

    public List<Flight> getFlightsBetweenAirports(Long originId, Long destinationId) {
        return flightRepo.findByOriginAirportIdAndDestinationAirportId(originId, destinationId);
    }
}
