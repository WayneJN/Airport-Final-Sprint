package com.wayne.airportAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportDTO {

    private Long id;

    @NotBlank(message = "Airport name is required")
    private String name;

    @NotBlank(message = "Airport code is required")
    private String code;

    @NotBlank(message = "City name is required")
    private String cityName;

    @NotBlank(message = "City state is required")
    private String cityState;
}
