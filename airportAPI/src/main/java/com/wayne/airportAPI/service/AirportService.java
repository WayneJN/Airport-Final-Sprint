package com.wayne.airportAPI.service;

import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.model.City;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.repository.AirportRepository;
import com.wayne.airportAPI.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
    }

    public Airport createAirport(Airport airport) {
        Long cityId = airport.getCity().getId();
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));
        airport.setCity(city);
        System.out.println("📦 Received airport: " + airport);
        System.out.println("🌆 City: " + (airport.getCity() != null ? airport.getCity().getId() : "null"));

        return airportRepository.save(airport);

    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

    public List<Passenger> getPassengersByAirportId(Long airportId) {
        Airport airport = getAirportById(airportId);
        return airport.getAircraft().stream()
                .flatMap(a -> a.getPassengers().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
