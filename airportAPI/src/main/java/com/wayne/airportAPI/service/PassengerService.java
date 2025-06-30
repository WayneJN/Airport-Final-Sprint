package com.wayne.airportAPI.service;

import com.wayne.airportAPI.model.City;
import com.wayne.airportAPI.model.Passenger;
import com.wayne.airportAPI.repository.CityRepository;
import com.wayne.airportAPI.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepo;

    @Autowired
    private CityRepository cityRepo;

    public List<Passenger> getAllPassengers() {
        return passengerRepo.findAll();
    }

    public Passenger getPassengerById(Long id) {
        return passengerRepo.findById(id).orElseThrow();
    }

    public Passenger createPassenger(Passenger passenger) {
        Long cityId = passenger.getCity().getId();
        City city = cityRepo.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));
        passenger.setCity(city);
        return passengerRepo.save(passenger);
    }

    public void deletePassenger(Long id) {
        passengerRepo.deleteById(id);
    }
}
