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
import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.model.CountryRequest;
import mx.com.ids.practice.repository.CountryRepository;
import mx.com.ids.practice.service.AirportService;

/**
 * 
 * @author joel.rubio
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestCountryServiceImpl {

	@Mock
	private CountryRepository countryRepository;
	
	@Mock
	private AirportService airportService;

	@Spy
	@InjectMocks
	private CountryServiceImpl countryService;
	
	
	@DisplayName("Add new country")
	@Test
	public void addNewCountry() {
		
		//given
		CountryRequest countryRequest = new CountryRequest("2342323", "Mexico", List.of(1, 2, 3));
		
		List<Airport> airports = List.of(new Airport(234234, "Cancun International Airport", null));
		Country country = new Country(countryRequest.getCode(), countryRequest.getName(), airports);
		
		//when
		Mockito.doReturn(airports).when(airportService).fromIdsToEntities(countryRequest.getAirportIds());
		Mockito.when(countryRepository.save(country)).thenReturn(country);
		
		//execute
		countryService.add(countryRequest);
		
		//then
		Mockito.verify(countryRepository, Mockito.times(1)).save(country);
	}
	
	@DisplayName("Find all countries")
	@Test
	public void findAllCountries() {
		
		//given
		List<Country> expectedCountries = List.of(new Country("23423423", "Mexico", null));
		Collection<Country> actualCountries;
		
		//when
		Mockito.when(countryRepository.findAll()).thenReturn(expectedCountries);
		
		//execute
		actualCountries = countryService.findAll();
		
		//then
		assertEquals(expectedCountries, actualCountries);
	}
	
	@DisplayName("Find country by id")
	@Test
	public void findCountryById() {
		
		//given
		long id = 2134234;
		Optional<Country> expectedCountry = Optional.of(new Country("23423423", "Mexico", null));
		Country actualCountry;
		
		//when
		Mockito.when(countryRepository.findById(id)).thenReturn(expectedCountry);
		
		//execute
		actualCountry = countryService.findById(id);
		
		//then
		assertEquals(expectedCountry.get(), actualCountry);
	}
	
	@DisplayName("Throw exception when the country id doesn't exist")
	@Test
	public void throwExceptionIfIdDoesntExist() {
		
		//given
		long id = 0;
		
		//when
		Mockito.when(countryRepository.findById(id)).thenThrow(EntityNotFoundException.class);
		
		//then
		assertThrows(EntityNotFoundException.class, () -> countryService.findById(id));
	}
	
	@DisplayName("Delete a country by id")
	@Test
	public void deleteCountryById() {
		
		//given
		long id = 132334;
		Country country = new Country("23423423", "Mexico", null);
		
		//when
		Mockito.doReturn(country).when(countryService).findById(id);
		Mockito.doNothing().when(countryRepository).delete(country);
		
		//execute
		countryService.delete(id);
		
		//then
		Mockito.verify(countryRepository, Mockito.times(1)).delete(country);
		
	}
	
	@DisplayName("Update a country by id")
	@Test
	public void updateCountryById() {
		
		//given
		long id = 12323;
		List<Airport> airports        = List.of(new Airport(232323, "Cancun International Airport", null));
		CountryRequest countryRequest = new CountryRequest("23423423", "Mexico", List.of(1, 2, 3));
		Country expectedCountry       = new Country("23423423", "Mexico", airports);
		
		//when
		Mockito.when(airportService.fromIdsToEntities(countryRequest.getAirportIds())).thenReturn(airports);
		Mockito.doReturn(expectedCountry).when(countryService).findById(id);
		Mockito.when(countryRepository.save(expectedCountry)).thenReturn(expectedCountry);
		
		//execute
		countryService.update(id, countryRequest);
		
		//then
		Mockito.verify(countryRepository, Mockito.times(1)).save(expectedCountry);
	}
}
