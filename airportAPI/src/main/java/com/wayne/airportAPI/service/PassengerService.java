package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.PassengerDTO;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepo;

    // Retrieve all passengers as entities
    public List<Passenger> getAllPassengers() {
        return passengerRepo.findAll();
    }

    // Retrieve all passengers as DTOs
    public List<PassengerDTO> getAllPassengerDTOs() {
        return passengerRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Get passenger by ID
    public Passenger getPassengerById(Long id) {
        return passengerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
    }

    // Create passenger from DTO
    public Passenger createPassengerFromDTO(PassengerDTO dto) {
        Passenger passenger = fromDTO(dto);
        return passengerRepo.save(passenger);
    }

    // Create passenger from entity
    public Passenger createPassenger(Passenger passenger) {
        return passengerRepo.save(passenger);
    }

    // Delete passenger by ID
    public void deletePassenger(Long id) {
        passengerRepo.deleteById(id);
    }

    // Convert DTO to Entity
    public Passenger fromDTO(PassengerDTO dto) {
        return Passenger.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .passportNumber(dto.getPassportNumber())
                .build();
    }

    // Convert Entity to DTO
    public PassengerDTO toDTO(Passenger passenger) {
        return PassengerDTO.builder()
                .id(passenger.getId())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .email(passenger.getEmail())
                .passportNumber(passenger.getPassportNumber())
                .build();
    }
}
