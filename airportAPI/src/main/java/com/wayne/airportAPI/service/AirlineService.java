package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.AirlineDTO;
import com.wayne.airportAPI.model.Airline;
import com.wayne.airportAPI.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirlineService {

    private final AirlineRepository airlineRepo;

    public List<AirlineDTO> getAllAirlines() {
        return airlineRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AirlineDTO> getAirlineById(Long id) {
        return airlineRepo.findById(id)
                .map(this::toDTO);
    }

    public AirlineDTO createAirline(Airline airline) {
        Airline saved = airlineRepo.save(airline);
        return toDTO(saved);
    }

    public Optional<AirlineDTO> updateAirline(Long id, Airline updatedAirline) {
        return airlineRepo.findById(id).map(existing -> {
            existing.setName(updatedAirline.getName());
            existing.setCode(updatedAirline.getCode());
            existing.setCountry(updatedAirline.getCountry());
            return toDTO(airlineRepo.save(existing));
        });
    }

    public boolean deleteAirline(Long id) {
        if (airlineRepo.existsById(id)) {
            airlineRepo.deleteById(id);
            return true;
        }
        return false;
    }

    private AirlineDTO toDTO(Airline airline) {
        return AirlineDTO.builder()
                .id(airline.getId())
                .name(airline.getName())
                .code(airline.getCode())
                .country(airline.getCountry())
                .build();
    }
}
