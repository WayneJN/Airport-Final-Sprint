package com.wayne.airportAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wayne.airportAPI.dto.AircraftDTO;
import com.wayne.airportAPI.model.Aircraft;
import com.wayne.airportAPI.service.AircraftService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AircraftController.class)
class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AircraftService aircraftService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAircraft_Valid_ReturnsOk() throws Exception {
        AircraftDTO dto = AircraftDTO.builder()
                .model("A320")
                .manufacturer("Airbus")
                .capacity(180)
                .build();

        Aircraft aircraft = Aircraft.builder()
                .id(1L)
                .model("A320")
                .manufacturer("Airbus")
                .capacity(180)
                .build();

        Mockito.when(aircraftService.fromDTO(Mockito.any())).thenReturn(aircraft);
        Mockito.when(aircraftService.createAircraft(Mockito.any())).thenReturn(aircraft);
        Mockito.when(aircraftService.toDTO(Mockito.any())).thenReturn(dto);

        mockMvc.perform(post("/api/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("A320"));
    }


    @Test
    void testGetAllAircraft_ReturnsOk() throws Exception {
        mockMvc.perform(get("/api/aircraft"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
