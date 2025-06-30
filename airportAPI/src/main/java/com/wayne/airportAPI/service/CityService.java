package com.wayne.airportAPI.service;

import com.wayne.airportAPI.model.City;
import com.wayne.airportAPI.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
