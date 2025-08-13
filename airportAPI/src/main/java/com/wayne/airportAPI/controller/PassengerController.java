package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.dto.PassengerDTO;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/passengers")
@CrossOrigin
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengerDTOs();
    }

    // ✅ Updated to use ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<?> getPassengerById(@PathVariable Long id) {
        System.out.println("Fetching passenger with ID: " + id);
        try {
            Passenger passenger = passengerService.getPassengerById(id);
            if (passenger == null) {
                return ResponseEntity.notFound().build(); // 404 if not found
            }
            PassengerDTO dto = passengerService.toDTO(passenger);
            return ResponseEntity.ok(dto); // 200 OK with DTO
        } catch (Exception e) {
            e.printStackTrace(); // Log full error
            return ResponseEntity.status(500).body("Error fetching passenger: " + e.getMessage());
        }
    }

    @PostMapping
    public PassengerDTO createPassenger(@Valid @RequestBody PassengerDTO dto) {
        Passenger passenger = passengerService.fromDTO(dto);
        Passenger saved = passengerService.createPassenger(passenger);
        return passengerService.toDTO(saved);
    }

    @PutMapping("/{id}")
    public PassengerDTO updatePassenger(@PathVariable Long id, @Valid @RequestBody PassengerDTO dto) {
        System.out.println("Received update for passenger ID: " + id);
        System.out.println("DTO: " + dto);

        try {
            Passenger passenger = passengerService.fromDTO(dto);
            System.out.println("Mapped Passenger: " + passenger);
            Passenger updated = passengerService.updatePassenger(id, passenger);
            System.out.println("Updated Passenger: " + updated);
            return passengerService.toDTO(updated);
        } catch (Exception e) {
            e.printStackTrace(); // Show full error in logs
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }
}
