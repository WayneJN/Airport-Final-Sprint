package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.AirportDTO;
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

    public AirportDTO toDTO(Airport airport) {
        return AirportDTO.builder()
                .id(airport.getId())
                .name(airport.getName())
                .code(airport.getCode())
                .cityName(airport.getCity() != null ? airport.getCity().getName() : null)
                .cityState(airport.getCity() != null ? airport.getCity().getState() : null)
                .build();
    }

    public Airport fromDTO(AirportDTO dto) {
        City city = cityRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(dto.getCityName()) &&
                        c.getState().equalsIgnoreCase(dto.getCityState()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("City not found for name/state"));

        return Airport.builder()
                .id(dto.getId())
                .name(dto.getName())
                .code(dto.getCode())
                .city(city)
                .build();
    }

    public List<AirportDTO> getAllAirportDTOs() {
        return airportRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
