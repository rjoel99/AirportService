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
	
	private static String uri;
	
	@BeforeAll
	public static void setUp() {
		uri = "http://localhost:8080/api/v1/airports";
	}
	
	@DisplayName("Get all airports")
	@Test
	public void getAllAirports() throws Exception {
		
		//given
		long id                      = 1;
		String name                  = "Cancun International Airport";
		Collection<Airport> airports = List.of(new Airport(id, name, null));
		
		//when
		Mockito.when(airportService.findAll()).thenReturn(airports);
		
		//execute
		mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
	}
	
	@DisplayName("Get airport by id")
	@Test
	public void getAirportById() throws Exception {
		
		//given
		long id         = 1;
		String name     = "Cancun International Airport";
		Airport airport = new Airport(id, name, null);
		
		//when
		Mockito.when(airportService.findById(id)).thenReturn(airport);
		
		//execute
		mockMvc.perform(get(uri + "/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(id), Long.class))
			.andExpect(jsonPath("$.name", is(name)));
	}
	
	@DisplayName("Add new airport")
	@Test
	public void addNewAirport() throws Exception {
		
		//given
		Airport airport = new Airport("Aeropuerto Internacional de la Ciudad de México");
		
		//when
		Mockito.doNothing().when(airportService).add(airport);
	
		//execute
		mockMvc.perform(post(uri)
							.content(new ObjectMapper().writeValueAsString(airport))
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated());
	}
	
	@DisplayName("Update an airport by id")
	@Test
	public void updateAirportById() throws Exception {
		
		//given
		long id         = 1;
		String name     = "Cancun International Airport";
		Airport airport = new Airport(id, name, null);
		
		//when
		Mockito.doNothing().when(airportService).update(id, airport);
		
		//execute
		mockMvc.perform(put(uri + "/{id}", id)
							.content(new ObjectMapper().writeValueAsString(airport))
							.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Airport updated")));
	}
	
	@DisplayName("Delete an airport by id")
	@Test
	public void deleteAirportById() throws Exception {
		
		//given
		long id = 1;
		
		//when
		Mockito.doNothing().when(airportService).delete(id);
		
		//execute
		mockMvc.perform(delete(uri + "/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Airport deleted")));
	}
}
