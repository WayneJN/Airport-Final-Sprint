package com.wayne.airportAPI.service;

import com.example.airportapi.model.City;
import com.example.airportapi.repository.CityRepository;
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

    public City createCity(City c) {
        return cityRepo.save(c);
    }

    public City getCityById(Long id) {
        return cityRepo.findById(id).orElseThrow();
    }
}
