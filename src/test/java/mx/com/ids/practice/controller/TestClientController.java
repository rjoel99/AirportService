package mx.com.ids.practice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import mx.com.ids.practice.model.AggregationRequest;
import mx.com.ids.practice.service.AggregationService;

/**
 * 
 * @author joel.rubio
 *
 */
@WebMvcTest(controllers = AggregationController.class)
public class TestClientController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AggregationService aggregationService;
	
	private static String uri;
	
	@BeforeAll
	public static void setUp() {
		uri = "http://localhost:8080/apiv1/clientes/add";
	}
	
	@DisplayName("Add new record of language, employee, country, and airport")
	@Test
	public void addNewRecord() throws Exception {
		
		//given
		AggregationRequest aggregationRequest = new AggregationRequest("John", "Doe", "Mexico", "Spanish", "Aeropuerto Internacional de la Ciudad de México");
		
		//when
		Mockito.doNothing().when(aggregationService).add(aggregationRequest);
	
		//execute
		mockMvc.perform(post(uri)
							.content(new ObjectMapper().writeValueAsString(aggregationRequest))
							.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.message", is("Record added")));
	}
}
