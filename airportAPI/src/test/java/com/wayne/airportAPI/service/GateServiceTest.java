package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.GateDTO;
import com.wayne.airportAPI.model.Airport;
import com.wayne.airportAPI.model.Gate;
import com.wayne.airportAPI.repository.GateRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GateServiceTest {

    @Mock
    private GateRepository gateRepo;

    @InjectMocks
    private GateService gateService;

    private AutoCloseable mocks;
    private Gate sampleGate;

    @BeforeEach
    void setUp() throws Exception {
        mocks = MockitoAnnotations.openMocks(this);

        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("St. John's International");
        airport.setCode("YYT");

        sampleGate = new Gate();
        sampleGate.setId(1L);
        sampleGate.setGateNumber("A1");
        sampleGate.setAirport(airport);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void testGetAllGates() {
        when(gateRepo.findAll()).thenReturn(List.of(sampleGate));

        List<GateDTO> result = gateService.getAllGates();

        assertEquals(1, result.size());
        assertEquals("A1", result.get(0).getGateNumber());
        assertEquals("YYT", result.get(0).getAirportCode());
    }

    @Test
    void testGetGateById_Found() {
        when(gateRepo.findById(1L)).thenReturn(Optional.of(sampleGate));

        Optional<GateDTO> result = gateService.getGateById(1L);

        assertTrue(result.isPresent());
        assertEquals("St. John's International", result.get().getAirportName());
    }

    @Test
    void testGetGateById_NotFound() {
        when(gateRepo.findById(99L)).thenReturn(Optional.empty());

        Optional<GateDTO> result = gateService.getGateById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateGate() {
        when(gateRepo.save(any())).thenReturn(sampleGate);

        GateDTO result = gateService.createGate(sampleGate);

        assertEquals("A1", result.getGateNumber());
        assertEquals("YYT", result.getAirportCode());
    }

    @Test
    void testUpdateGate_Found() {
        Gate updatedGate = new Gate();
        updatedGate.setGateNumber("B2");
        updatedGate.setAirport(sampleGate.getAirport());

        when(gateRepo.findById(1L)).thenReturn(Optional.of(sampleGate));
        when(gateRepo.save(any())).thenReturn(updatedGate);

        Optional<GateDTO> result = gateService.updateGate(1L, updatedGate);

        assertTrue(result.isPresent());
        assertEquals("B2", result.get().getGateNumber());
    }

    @Test
    void testUpdateGate_NotFound() {
        when(gateRepo.findById(99L)).thenReturn(Optional.empty());

        Optional<GateDTO> result = gateService.updateGate(99L, sampleGate);

        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteGate_Success() {
        when(gateRepo.existsById(1L)).thenReturn(true);

        boolean result = gateService.deleteGate(1L);

        assertTrue(result);
        verify(gateRepo).deleteById(1L);
    }

    @Test
    void testDeleteGate_Failure() {
        when(gateRepo.existsById(2L)).thenReturn(false);

        boolean result = gateService.deleteGate(2L);

        assertFalse(result);
        verify(gateRepo, never()).deleteById(anyLong());
    }
}
