package com.wayne.airportAPI.controller;

import com.wayne.airportAPI.dto.CityDTO;
import com.wayne.airportAPI.model.City;
import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityDTO> getAllCities() {
        return cityService.getAllCities().stream()
                .map(cityService::toDTO)
                .toList();
    }

    @PostMapping
    public CityDTO createCity(@Valid @RequestBody CityDTO dto) {
        City city = cityService.fromDTO(dto);
        City saved = cityService.createCity(city);
        return cityService.toDTO(saved);
    }

    @GetMapping("/{id}/airports")
    public Set<Airport> getAirportsByCity(@PathVariable Long id) {
        return cityService.getCityById(id).getAirports();
    }
}
