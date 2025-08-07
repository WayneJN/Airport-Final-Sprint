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

    public List<Passenger> getAllPassengers() {
        return passengerRepo.findAll();
    }

    public List<PassengerDTO> getAllPassengerDTOs() {
        return passengerRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Passenger getPassengerById(Long id) {
        return passengerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
    }

    public Passenger createPassengerFromDTO(PassengerDTO dto) {
        Passenger passenger = toEntity(dto);
        return passengerRepo.save(passenger);
    }

    public void deletePassenger(Long id) {
        passengerRepo.deleteById(id);
    }

    public Passenger toEntity(PassengerDTO dto) {
        return Passenger.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .passportNumber(dto.getPassportNumber())
                .build();
    }

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
