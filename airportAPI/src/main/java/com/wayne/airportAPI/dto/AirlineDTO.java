package com.wayne.airportAPI.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirlineDTO {

    private Long id;
    private String name;
    private String code;
    private String country;
}
