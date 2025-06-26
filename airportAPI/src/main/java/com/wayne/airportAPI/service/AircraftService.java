package com.wayne.airportAPI.service;

import com.wayne.airportAPI.model.Aircraft;
import com.wayne.airportAPI.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepo;

    public List<Aircraft> getAllAircraft() {
        return aircraftRepo.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepo.findById(id).orElseThrow();
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        return aircraftRepo.save(aircraft);
    }

    public void deleteAircraft(Long id) {
        aircraftRepo.deleteById(id);
    }
}
