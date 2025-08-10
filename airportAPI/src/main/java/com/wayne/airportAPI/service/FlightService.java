package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.FlightDTO;
import com.wayne.airportAPI.dto.FlightResponseDTO;
import com.wayne.airportAPI.model.*;
import com.wayne.airportAPI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Autowired
    private AirlineRepository airlineRepo;

    @Autowired
    private GateRepository gateRepo;

    public List<FlightResponseDTO> getAllFlights() {
        return flightRepo.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<FlightResponseDTO> getFlightById(Long id) {
        return flightRepo.findById(id)
                .map(this::toResponseDTO);
    }

    public Flight createFlight(Flight flight) {
        Aircraft aircraft = aircraftRepo.findById(flight.getAircraft().getId())
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        Airport origin = airportRepo.findById(flight.getOriginAirport().getId())
                .orElseThrow(() -> new RuntimeException("Origin airport not found"));

        Airport destination = airportRepo.findById(flight.getDestinationAirport().getId())
                .orElseThrow(() -> new RuntimeException("Destination airport not found"));

        Airline airline = airlineRepo.findById(flight.getAirline().getId())
                .orElseThrow(() -> new RuntimeException("Airline not found"));

        Gate gate = gateRepo.findById(flight.getGate().getId())
                .orElseThrow(() -> new RuntimeException("Gate not found"));

        Set<Passenger> passengers = flight.getPassengers().stream()
                .map(p -> passengerRepo.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Passenger not found")))
                .collect(Collectors.toSet());

        flight.setAircraft(aircraft);
        flight.setOriginAirport(origin);
        flight.setDestinationAirport(destination);
        flight.setAirline(airline);
        flight.setGate(gate);
        flight.setPassengers(passengers);

        return flightRepo.save(flight);
    }

    public Flight createFlightFromDTO(FlightDTO dto) {
        Flight flight = toEntity(dto);
        return createFlight(flight);
    }

    public List<Flight> getFlightsBetweenAirports(Long originId, Long destinationId) {
        return flightRepo.findByOriginAirportIdAndDestinationAirportId(originId, destinationId);
    }

    public void deleteFlight(Long id) {
        flightRepo.deleteById(id);
    }

    public Flight toEntity(FlightDTO dto) {
        Flight flight = new Flight();
        flight.setId(dto.getId());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setArrivalTime(dto.getArrivalTime());

        flight.setAircraft(Aircraft.builder().id(dto.getAircraftId()).build());
        flight.setOriginAirport(Airport.builder().id(dto.getOriginAirportId()).build());
        flight.setDestinationAirport(Airport.builder().id(dto.getDestinationAirportId()).build());
        flight.setAirline(Airline.builder().id(dto.getAirlineId()).build());
        flight.setGate(Gate.builder().id(dto.getGateId()).build());

        if (dto.getPassengerIds() != null) {
            Set<Passenger> passengers = dto.getPassengerIds().stream()
                    .map(id -> Passenger.builder().id(id).build())
                    .collect(Collectors.toSet());
            flight.setPassengers(passengers);
        }

        return flight;
    }

    public FlightResponseDTO toResponseDTO(Flight flight) {
        return FlightResponseDTO.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .aircraftModel(flight.getAircraft().getModel())
                .originAirportCode(flight.getOriginAirport().getCode())
                .destinationAirportCode(flight.getDestinationAirport().getCode())
                .airlineName(flight.getAirline().getName())
                .gateNumber(flight.getGate().getGateNumber())
                .passengerIds(flight.getPassengers().stream()
                        .map(Passenger::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Flight updateFlightFromDTO(Long id, FlightDTO dto) {
        Flight existing = flightRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + id));

        existing.setFlightNumber(dto.getFlightNumber());
        existing.setDepartureTime(dto.getDepartureTime());
        existing.setArrivalTime(dto.getArrivalTime());

        Aircraft aircraft = aircraftRepo.findById(dto.getAircraftId())
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));
        Airport origin = airportRepo.findById(dto.getOriginAirportId())
                .orElseThrow(() -> new RuntimeException("Origin airport not found"));
        Airport destination = airportRepo.findById(dto.getDestinationAirportId())
                .orElseThrow(() -> new RuntimeException("Destination airport not found"));
        Airline airline = airlineRepo.findById(dto.getAirlineId())
                .orElseThrow(() -> new RuntimeException("Airline not found"));
        Gate gate = gateRepo.findById(dto.getGateId())
                .orElseThrow(() -> new RuntimeException("Gate not found"));

        Set<Passenger> passengers = dto.getPassengerIds().stream()
                .map(passengerId -> passengerRepo.findById(passengerId)
                        .orElseThrow(() -> new RuntimeException("Passenger not found with ID: " + passengerId)))
                .collect(Collectors.toSet());


        existing.setAircraft(aircraft);
        existing.setOriginAirport(origin);
        existing.setDestinationAirport(destination);
        existing.setAirline(airline);
        existing.setGate(gate);
        existing.setPassengers(passengers);

        return flightRepo.save(existing);
    }

}
