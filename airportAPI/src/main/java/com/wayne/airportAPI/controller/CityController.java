package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.model.City;
import com.wayne.airportAPI.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @PostMapping
    public City createCity(@Valid @RequestBody City city) {
        return cityService.createCity(city);
    }

    @GetMapping("/{id}/airports")
    public Set<Airport> getAirportsByCity(@PathVariable Long id) {
        return cityService.getCityById(id).getAirports();
    }
}
