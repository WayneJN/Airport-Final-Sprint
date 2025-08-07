package com.wayne.airportAPI.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportDTO {

    private Long id;
    private String name;
    private String code;
    private String cityName;
    private String cityState;
}
