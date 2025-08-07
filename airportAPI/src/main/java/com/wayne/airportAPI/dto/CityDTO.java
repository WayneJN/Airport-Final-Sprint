package com.wayne.airportAPI.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDTO {

    private Long id;
    private String name;
    private String state;
    private Long population;
}
