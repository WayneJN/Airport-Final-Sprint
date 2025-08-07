package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.dto.PassengerDTO;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passengers")
@CrossOrigin
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengerDTOs();
    }

    @GetMapping("/{id}")
    public PassengerDTO getPassengerById(@PathVariable Long id) {
        Passenger passenger = passengerService.getPassengerById(id);
        return passengerService.toDTO(passenger);
    }

    @PostMapping
    public PassengerDTO createPassenger(@Valid @RequestBody PassengerDTO dto) {
        Passenger passenger = passengerService.fromDTO(dto);
        Passenger saved = passengerService.createPassenger(passenger);
        return passengerService.toDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }
}
