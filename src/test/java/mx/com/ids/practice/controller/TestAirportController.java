package mx.com.ids.practice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.ids.practice.entity.Airport;
import mx.com.ids.practice.service.AirportService;

/**
 * 
 * @author joel.rubio
 *
 */
@WebMvcTest(controllers = AirportController.class)
public class TestAirportController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AirportService airportService;
	
	private static String url;
	
	@BeforeAll
	public static void setUp() {
		url = "http://localhost:8080/api/v1/airports";
	}
	
	@DisplayName("Get all airports with status 200")
	@Test
	public void getAllAirports() throws Exception {
		
		//given
		long id                      = 1;
		String name                  = "Cancun International Airport";
		Collection<Airport> airports = List.of(new Airport(id, name, null));
		
		//when
		Mockito.when(airportService.findAll()).thenReturn(airports);
		
		//execute
		mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
	}
	
	@DisplayName("Get airport by id with status 200")
	@Test
	public void getAirportById() throws Exception {
		
		//given
		long id         = 1;
		String name     = "Cancun International Airport";
		Airport airport = new Airport(id, name, null);
		
		//when
		Mockito.when(airportService.findById(id)).thenReturn(airport);
		
		//execute
		mockMvc.perform(get(url + "/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(id), Long.class))
			.andExpect(jsonPath("$.name", is(name)));
	}
	
	@DisplayName("Get status 404 if airport id doesn't exist")
	@Test
	public void throwExceptionIfIdDoesntExist() throws Exception {
		
		//given
		long id = 1;
		String message = "The airport doesn't exist";
		
		//when
		Mockito.when(airportService.findById(id)).thenThrow(new EntityNotFoundException(message));
		
		//execute
		mockMvc.perform(get(url + "/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.status", is(404)))
			.andExpect(jsonPath("$.message", is(message)))
			.andExpect(jsonPath("$.path", is("/api/v1/airports/" + id)));
	}
	
	@DisplayName("Add new airport with status 201")
	@Test
	public void addNewAirport() throws Exception {
		
		//given
		Airport airport = new Airport("Aeropuerto Internacional de la Ciudad de México");
		
		//when
		Mockito.when(airportService.add(airport)).thenReturn(airport);
	
		//execute
		mockMvc.perform(post(url)
							.content(new ObjectMapper().writeValueAsString(airport))
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated());
	}
	
	@DisplayName("Update an airport by id with status 200")
	@Test
	public void updateAirportById() throws Exception {
		
		//given
		long id         = 1;
		String name     = "Cancun International Airport";
		Airport airport = new Airport(id, name, null);
		
		//when
		Mockito.doNothing().when(airportService).update(id, airport);
		
		//execute
		mockMvc.perform(put(url + "/{id}", id)
							.content(new ObjectMapper().writeValueAsString(airport))
							.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Airport updated")));
	}
	
	@DisplayName("Delete an airport by id with status 200")
	@Test
	public void deleteAirportById() throws Exception {
		
		//given
		long id = 1;
		
		//when
		Mockito.doNothing().when(airportService).delete(id);
		
		//execute
		mockMvc.perform(delete(url + "/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Airport deleted")));
	}
}
