package com.wayne.airportAPI.service;

import com.wayne.airportAPI.dto.FlightDTO;
import com.wayne.airportAPI.dto.FlightResponseDTO;
import com.wayne.airportAPI.model.*;
import com.wayne.airportAPI.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepository flightRepo;
    @Mock
    private AircraftRepository aircraftRepo;
    @Mock
    private AirportRepository airportRepo;
    @Mock
    private PassengerRepository passengerRepo;
    @Mock
    private AirlineRepository airlineRepo;
    @Mock
    private GateRepository gateRepo;

    private Flight mockFlight;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockFlight = Flight.builder()
                .id(1L)
                .flightNumber("AC101")
                .departureTime(LocalDateTime.now())
                .arrivalTime(LocalDateTime.now().plusHours(2))
                .aircraft(Aircraft.builder().id(1L).model("Boeing 737").build())
                .originAirport(Airport.builder().id(1L).code("YYZ").build())
                .destinationAirport(Airport.builder().id(2L).code("YVR").build())
                .airline(Airline.builder().id(1L).name("Air Canada").build())
                .gate(Gate.builder().id(1L).gateNumber("A1").build())
                .passengers(Set.of(Passenger.builder().id(1L).build()))
                .build();
    }

    @Test
    void testGetFlightById_Found() {
        when(flightRepo.findById(1L)).thenReturn(Optional.of(mockFlight));
        Optional<FlightResponseDTO> result = flightService.getFlightById(1L);
        assertTrue(result.isPresent());
        assertEquals("AC101", result.get().getFlightNumber());
    }


    @Test
    void testGetFlightById_NotFound() {
        when(flightRepo.findById(99L)).thenReturn(Optional.empty());

        Optional<FlightResponseDTO> result = flightService.getFlightById(99L);
        assertFalse(result.isPresent());
    }


    @Test
    void testCreateFlight_Success() {
        when(aircraftRepo.findById(1L)).thenReturn(Optional.of(mockFlight.getAircraft()));
        when(airportRepo.findById(1L)).thenReturn(Optional.of(mockFlight.getOriginAirport()));
        when(airportRepo.findById(2L)).thenReturn(Optional.of(mockFlight.getDestinationAirport()));
        when(airlineRepo.findById(1L)).thenReturn(Optional.of(mockFlight.getAirline()));
        when(gateRepo.findById(1L)).thenReturn(Optional.of(mockFlight.getGate()));
        when(passengerRepo.findById(1L)).thenReturn(Optional.of(Passenger.builder().id(1L).build()));
        when(flightRepo.save(any(Flight.class))).thenReturn(mockFlight);

        Flight result = flightService.createFlight(mockFlight);
        assertEquals("AC101", result.getFlightNumber());
        verify(flightRepo, times(1)).save(any(Flight.class));
    }

    @Test
    void testDeleteFlight() {
        flightService.deleteFlight(1L);
        verify(flightRepo, times(1)).deleteById(1L);
    }

    @Test
    void testGetFlightsBetweenAirports() {
        when(flightRepo.findByOriginAirportIdAndDestinationAirportId(1L, 2L))
                .thenReturn(List.of(mockFlight));

        List<Flight> results = flightService.getFlightsBetweenAirports(1L, 2L);
        assertEquals(1, results.size());
        assertEquals("AC101", results.get(0).getFlightNumber());
    }
//Edge case, missing passenger, missing Aircraft
    @Test
    void testCreateFlight_MissingPassenger_ThrowsException() {
        when(aircraftRepo.findById(1L)).thenReturn(Optional.of(mockFlight.getAircraft()));
        when(airportRepo.findById(1L)).thenReturn(Optional.of(mockFlight.getOriginAirport()));
        when(airportRepo.findById(2L)).thenReturn(Optional.of(mockFlight.getDestinationAirport()));
        when(airlineRepo.findById(1L)).thenReturn(Optional.of(mockFlight.getAirline()));
        when(gateRepo.findById(1L)).thenReturn(Optional.of(mockFlight.getGate()));
        when(passengerRepo.findById(1L)).thenReturn(Optional.empty()); // simulate missing passenger

        RuntimeException ex = assertThrows(RuntimeException.class, () -> flightService.createFlight(mockFlight));
        assertEquals("Passenger not found", ex.getMessage());
    }

    @Test
    void testCreateFlight_MissingAircraft_ThrowsException() {
        when(aircraftRepo.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> flightService.createFlight(mockFlight));
        assertEquals("Aircraft not found", ex.getMessage());
    }
//DTO conversion testing. toentity() and toresponse()
@Test
void testToEntity_ConvertsDTOToFlight() {
    FlightDTO dto = FlightDTO.builder()
            .id(1L)
            .flightNumber("AC101")
            .departureTime(LocalDateTime.now())
            .arrivalTime(LocalDateTime.now().plusHours(2))
            .aircraftId(1L)
            .originAirportId(1L)
            .destinationAirportId(2L)
            .airlineId(1L)
            .gateId(1L)
            .passengerIds(Set.of(1L))
            .build();

    Flight flight = flightService.toEntity(dto);
    assertEquals("AC101", flight.getFlightNumber());
    assertEquals(1L, flight.getAircraft().getId());
    assertEquals(1L, flight.getOriginAirport().getId());
    assertEquals(2L, flight.getDestinationAirport().getId());
    assertEquals(1L, flight.getAirline().getId());
    assertEquals(1L, flight.getGate().getId());
    assertEquals(1, flight.getPassengers().size());
}
    @Test
    void testToResponseDTO_ConvertsFlightToDTO() {
        FlightResponseDTO dto = flightService.toResponseDTO(mockFlight);
        assertEquals("AC101", dto.getFlightNumber());
        assertEquals("YYZ", dto.getOriginAirportCode());
        assertEquals("YVR", dto.getDestinationAirportCode());
        assertEquals("Air Canada", dto.getAirlineName());
        assertEquals("Boeing 737", dto.getAircraftModel());
        assertEquals("A1", dto.getGateNumber());
        assertTrue(dto.getPassengerIds().contains(1L));
    }



}
