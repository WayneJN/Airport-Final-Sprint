package com.wayne.airportAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wayne.airportAPI.dto.PassengerDTO;
import com.wayne.airportAPI.service.PassengerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PassengerController.class)
class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreatePassenger_InvalidEmail_ReturnsBadRequest() throws Exception {
        PassengerDTO dto = PassengerDTO.builder()
                .firstName("Wayne")
                .lastName("Tester")
                .phoneNumber("1234567890")
                .email("not-an-email") // invalid format
                .passportNumber("P1234567")
                .cityId(1L)
                .build();

        mockMvc.perform(post("/api/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());


    }
}
