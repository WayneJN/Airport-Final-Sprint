package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.AirlineDTO;
import com.wayne.airportAPI.model.Airline;
import com.wayne.airportAPI.repository.AirlineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirlineServiceTest {

    @Mock
    private AirlineRepository airlineRepo;

    @InjectMocks
    private AirlineService airlineService;

    private Airline sampleAirline;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleAirline = new Airline();
        sampleAirline.setId(1L);
        sampleAirline.setName("WayneAir");
        sampleAirline.setCode("WA");
        sampleAirline.setCountry("Canada");

    }

    @Test
    void testGetAllAirlines() {
        when(airlineRepo.findAll()).thenReturn(List.of(sampleAirline));

        List<AirlineDTO> result = airlineService.getAllAirlines();

        assertEquals(1, result.size());
        assertEquals("WayneAir", result.get(0).getName());
    }

    @Test
    void testGetAirlineById_Found() {
        when(airlineRepo.findById(1L)).thenReturn(Optional.of(sampleAirline));

        Optional<AirlineDTO> result = airlineService.getAirlineById(1L);

        assertTrue(result.isPresent());
        assertEquals("WA", result.get().getCode());
    }

    @Test
    void testGetAirlineById_NotFound() {
        when(airlineRepo.findById(2L)).thenReturn(Optional.empty());

        Optional<AirlineDTO> result = airlineService.getAirlineById(2L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateAirline() {
        when(airlineRepo.save(any())).thenReturn(sampleAirline);

        AirlineDTO result = airlineService.createAirline(sampleAirline);

        assertEquals("Canada", result.getCountry());
    }

    @Test
    void testUpdateAirline_Found() {
        Airline updated = new Airline();
        updated.setName("NewName");
        updated.setCode("NN");
        updated.setCountry("USA");

        when(airlineRepo.findById(1L)).thenReturn(Optional.of(sampleAirline));
        when(airlineRepo.save(any())).thenReturn(updated);

        Optional<AirlineDTO> result = airlineService.updateAirline(1L, updated);

        assertTrue(result.isPresent());
        assertEquals("NewName", result.get().getName());
    }

    @Test
    void testUpdateAirline_NotFound() {
        when(airlineRepo.findById(99L)).thenReturn(Optional.empty());

        Optional<AirlineDTO> result = airlineService.updateAirline(99L, sampleAirline);

        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteAirline_Success() {
        when(airlineRepo.existsById(1L)).thenReturn(true);

        boolean result = airlineService.deleteAirline(1L);

        assertTrue(result);
        verify(airlineRepo).deleteById(1L);
    }

    @Test
    void testDeleteAirline_Failure() {
        when(airlineRepo.existsById(2L)).thenReturn(false);

        boolean result = airlineService.deleteAirline(2L);

        assertFalse(result);
        verify(airlineRepo, never()).deleteById(anyLong());
    }
}
