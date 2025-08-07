package com.wayne.airportAPI.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AircraftDTO {

    private Long id;
    private String model;
    private String manufacturer;
    private int capacity;
}
