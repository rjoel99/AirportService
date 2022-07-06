package mx.com.ids.practice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.com.ids.practice.entity.Airport;
import mx.com.ids.practice.repository.AirportRepository;

/**
 * 
 * @author joel.rubio
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestAirportServiceImpl {

	@Mock
	private AirportRepository airportRepository;

	@Spy
	@InjectMocks
	private AirportServiceImpl airportService;
	
	
	@DisplayName("Find all airports")
	@Test
	public void findAllAirports() {
		
		//given
		List<Airport> expectedAirports = List.of(new Airport(1, "Cancun International Airport", null));
		Collection<Airport> actualAirports;
		
		//when
		Mockito.when(airportRepository.findAll()).thenReturn(expectedAirports);
		
		//execute
		actualAirports = airportService.findAll();
		
		//then
		assertEquals(expectedAirports, actualAirports);
	}
	
	@DisplayName("Find airport by id")
	@Test
	public void findAirportById() {
		
		//given
		long id = 1;
		Optional<Airport> expectedAirport = Optional.of(new Airport(id, "Cancun International Airport", null));
		Airport actualAirport;
		
		//when
		Mockito.when(airportRepository.findById(id)).thenReturn(expectedAirport);
		
		//execute
		actualAirport = airportService.findById(id);
		
		//then
		assertEquals(expectedAirport.get(), actualAirport);
	}
	
	@DisplayName("Find airport by name")
	@Test
	public void findAirportByName() {
		
		//given
		long id     = 1;
		String name = "Cancun International Airport";
		Optional<Airport> expectedAirport = Optional.of(new Airport(id, name, null));
		Optional<Airport> actualAirport;
		
		//when
		Mockito.when(airportRepository.findByName(name)).thenReturn(expectedAirport);
		
		//execute
		actualAirport = airportService.findByName(name);
		
		//then
		assertEquals(expectedAirport, actualAirport);
	}
	
	@DisplayName("Throw exception when the airport id doesn't exist")
	@Test
	public void throwExceptionIfIdDoesntExist() {
		
		//given
		long id = 0;
		
		//when
		Mockito.when(airportRepository.findById(id)).thenThrow(EntityNotFoundException.class);
		
		//then
		assertThrows(EntityNotFoundException.class, () -> airportService.findById(id));
	}
	
	@DisplayName("Add new airport")
	@Test
	public void addNewAirport() {
		
		//given
		Airport airport = new Airport(1223, "Cancun International Airport", null);
		
		//when
		Mockito.when(airportRepository.save(airport)).thenReturn(airport);
		
		//execute
		airportService.add(airport);
		
		//then
		Mockito.verify(airportRepository, Mockito.times(1)).save(airport);
	}
	
	@DisplayName("Update an airport by id")
	@Test
	public void updateAirportById() {
		
		//given
		long id = 1;
		Airport aiportRequest = new Airport(id, "Cancun International Airport", null);
		Airport expectedAirport = new Airport(id, "Cancun International Airport", null);
		
		//when
		Mockito.doReturn(expectedAirport).when(airportService).findById(id);
		Mockito.when(airportRepository.save(expectedAirport)).thenReturn(expectedAirport);
		
		//execute
		airportService.update(id, aiportRequest);
		
		//then
		Mockito.verify(airportRepository, Mockito.times(1)).save(expectedAirport);
	}
	
	@DisplayName("Delete an airport by id")
	@Test
	public void deleteAirportById() {
		
		//given
		long id = 1;
		Airport airport = new Airport(id, "Cancun International Airport", null);
		
		//when
		Mockito.doReturn(airport).when(airportService).findById(id);
		Mockito.doNothing().when(airportRepository).delete(airport);
		
		//execute
		airportService.delete(id);
		
		//then
		Mockito.verify(airportRepository, Mockito.times(1)).delete(airport);
		
	}
}
