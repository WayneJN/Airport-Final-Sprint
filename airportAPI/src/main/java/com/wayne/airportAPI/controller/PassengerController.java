package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.model.Aircraft;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/passengers")
@CrossOrigin
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping
    public Passenger createPassenger(@Valid @RequestBody Passenger passenger) {
        return passengerService.createPassenger(passenger);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }

    @GetMapping("/{id}/aircraft")
    public Set<Aircraft> getAircraftByPassenger(@PathVariable Long id) {
        return passengerService.getPassengerById(id).getAircraft();
    }
}
