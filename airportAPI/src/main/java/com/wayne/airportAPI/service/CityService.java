package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.CityDTO;
import com.wayne.airportAPI.model.City;
import com.wayne.airportAPI.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepo;

    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    public City getCityById(Long id) {
        return cityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with ID: " + id));
    }

    public City createCity(City city) {
        return cityRepo.save(city);
    }

    public void deleteCity(Long id) {
        if (!cityRepo.existsById(id)) {
            throw new RuntimeException("Cannot delete — city not found with ID: " + id);
        }
        cityRepo.deleteById(id);
    }

    public CityDTO toDTO(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .state(city.getState())
                .population(city.getPopulation())
                .build();
    }

    public City fromDTO(CityDTO dto) {
        return City.builder()
                .id(dto.getId())
                .name(dto.getName())
                .state(dto.getState())
                .population(dto.getPopulation())
                .build();
    }

    public List<CityDTO> getAllCityDTOs() {
        return cityRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
