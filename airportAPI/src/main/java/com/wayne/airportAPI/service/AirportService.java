package com.wayne.airportAPI.service;

import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepo;

    public List<Airport> getAllAirports() {
        return airportRepo.findAll();
    }

    public Airport createAirport(Airport airport) {
        return airportRepo.save(airport);
    }

    public Airport getAirportById(Long id) {
        return airportRepo.findById(id).orElseThrow();
    }

    public void deleteAirport(Long id) {
        airportRepo.deleteById(id);
    }
}
