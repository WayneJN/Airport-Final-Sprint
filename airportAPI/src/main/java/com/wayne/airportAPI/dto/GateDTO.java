package com.wayne.airportAPI.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GateDTO {

    private Long id;
    private String gateNumber;
    private String airportName;
    private String airportCode;
}
