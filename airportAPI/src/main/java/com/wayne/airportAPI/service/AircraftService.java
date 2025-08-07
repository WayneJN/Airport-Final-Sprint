package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.AircraftDTO;
import com.wayne.airportAPI.model.Aircraft;
import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.repository.AircraftRepository;
import com.wayne.airportAPI.repository.AirportRepository;
import com.wayne.airportAPI.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository aircraftRepo;

    @Autowired
    private AirportRepository airportRepo;

    @Autowired
    private PassengerRepository passengerRepo;

    public List<Aircraft> getAllAircraft() {
        return aircraftRepo.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        Set<Airport> resolvedAirports = aircraft.getAirports().stream()
                .map(a -> airportRepo.findById(a.getId())
                        .orElseThrow(() -> new RuntimeException("Airport not found")))
                .collect(Collectors.toSet());

        Set<Passenger> resolvedPassengers = aircraft.getPassengers().stream()
                .map(p -> passengerRepo.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Passenger not found")))
                .collect(Collectors.toSet());

        aircraft.setAirports(resolvedAirports);
        aircraft.setPassengers(resolvedPassengers);

        return aircraftRepo.save(aircraft);
    }

    public AircraftDTO toDTO(Aircraft aircraft) {
        return AircraftDTO.builder()
                .id(aircraft.getId())
                .model(aircraft.getModel())
                .manufacturer(aircraft.getManufacturer())
                .capacity(aircraft.getCapacity())
                .build();
    }

    public Aircraft fromDTO(AircraftDTO dto) {
        return Aircraft.builder()
                .id(dto.getId())
                .model(dto.getModel())
                .manufacturer(dto.getManufacturer())
                .capacity(dto.getCapacity())
                .build();
    }

    public List<AircraftDTO> getAllAircraftDTOs() {
        return aircraftRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteAircraft(Long id) {
        aircraftRepo.deleteById(id);
    }
}
