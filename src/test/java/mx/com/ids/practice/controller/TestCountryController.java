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

import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.model.CountryRequest;
import mx.com.ids.practice.service.CountryService;

/**
 * 
 * @author joel.rubio
 *
 */
@WebMvcTest(controllers = CountryController.class)
public class TestCountryController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CountryService countryService;
	
	private static String url;
	
	@BeforeAll
	public static void setUp() {
		url = "http://localhost:8080/api/v1/countries";
	}
	
	@DisplayName("Get all countries")
	@Test
	public void getAllCountries() throws Exception {
		
		//given
		long id                       = 1;
		String code                   = "srte4t3453453";
		String name                   = "Mexico";
		Collection<Country> countries = List.of(new Country(id, code, name, null));
		
		//when
		Mockito.when(countryService.findAll()).thenReturn(countries);
		
		//execute
		mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
	}
	
	@DisplayName("Get country by id")
	@Test
	public void getCountryById() throws Exception {
		
		//given
		long id         = 1;
		String code     = "ertgq34534a4334";
		String name     = "Mexico";
		Country country = new Country(id, code, name, null);
		
		//when
		Mockito.when(countryService.findById(id)).thenReturn(country);
		
		//execute
		mockMvc.perform(get(url + "/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(id), Long.class))
			.andExpect(jsonPath("$.name", is(name)));
	}
	
	@DisplayName("Add new country")
	@Test
	public void addNewCountry() throws Exception {
		
		//given
		CountryRequest countryRequest = new CountryRequest("345dsdfq453", "Mexico", List.of(1, 2, 3));
		
		//when
		Mockito.doNothing().when(countryService).add(countryRequest);
	
		//execute
		mockMvc.perform(post(url)
							.content(new ObjectMapper().writeValueAsString(countryRequest))
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated());
	}
	
	@DisplayName("Update a country by id")
	@Test
	public void updateCountryById() throws Exception {
		
		//given
		long id                       = 1;
		String code                   = "23423sdfwerf234";
		String name                   = "Mexico";
		CountryRequest countryRequest = new CountryRequest(code, name, null);
		
		//when
		Mockito.doNothing().when(countryService).update(id, countryRequest);
		
		//execute
		mockMvc.perform(put(url + "/{id}", id)
							.content(new ObjectMapper().writeValueAsString(countryRequest))
							.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Country updated")));
	}
	
	@DisplayName("Delete a country by id")
	@Test
	public void deleteContryById() throws Exception {
		
		//given
		long id = 1;
		
		//when
		Mockito.doNothing().when(countryService).delete(id);
		
		//execute
		mockMvc.perform(delete(url + "/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Country deleted")));
	}
}
