package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.PassengerDTO;
import com.wayne.airportAPI.model.City;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.repository.CityRepository;
import com.wayne.airportAPI.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepo;

    @Autowired
    private CityRepository cityRepo;

    public List<Passenger> getAllPassengers() {
        return passengerRepo.findAll();
    }

    public List<PassengerDTO> getAllPassengerDTOs() {
        return passengerRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Passenger getPassengerById(Long id) {
        Passenger passenger = passengerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        System.out.println("Passenger: " + passenger);
        System.out.println("City: " + passenger.getCity());
        System.out.println("Phone: " + passenger.getPhoneNumber());

        return passenger;
    }

    public Passenger createPassengerFromDTO(PassengerDTO dto) {
        Passenger passenger = fromDTO(dto);
        return passengerRepo.save(passenger);
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepo.save(passenger);
    }

    public Passenger updatePassenger(Long id, Passenger passenger) {
        Passenger existing = getPassengerById(id);
        existing.setFirstName(passenger.getFirstName());
        existing.setLastName(passenger.getLastName());
        existing.setEmail(passenger.getEmail());
        existing.setPassportNumber(passenger.getPassportNumber());
        existing.setPhoneNumber(passenger.getPhoneNumber());
        existing.setCity(passenger.getCity());
        return passengerRepo.save(existing);
    }

    public void deletePassenger(Long id) {
        passengerRepo.deleteById(id);
    }

    public Passenger fromDTO(PassengerDTO dto) {
        Passenger passenger = Passenger.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .passportNumber(dto.getPassportNumber())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        if (dto.getCityId() != null) {
            City city = cityRepo.findById(dto.getCityId())
                    .orElseThrow(() -> new RuntimeException("City not found with ID: " + dto.getCityId()));
            passenger.setCity(city);
        }

        return passenger;
    }

    public PassengerDTO toDTO(Passenger passenger) {
        return PassengerDTO.builder()
                .id(passenger.getId())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .email(passenger.getEmail())
                .passportNumber(passenger.getPassportNumber())
                .phoneNumber(passenger.getPhoneNumber())
                .cityId(passenger.getCity() != null ? passenger.getCity().getId() : null)
                .build();
    }
}
