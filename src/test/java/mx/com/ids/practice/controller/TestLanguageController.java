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

import mx.com.ids.practice.entity.Language;
import mx.com.ids.practice.model.LanguageRequest;
import mx.com.ids.practice.service.LanguageService;

/**
 * 
 * @author joel.rubio
 *
 */
@WebMvcTest(controllers = LanguageController.class)
public class TestLanguageController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LanguageService languageService;
	
	private static String url;
	
	@BeforeAll
	public static void setUp() {
		url = "http://localhost:8080/api/v1/languages";
	}
	
	@DisplayName("Get all languages")
	@Test
	public void getAllLanguages() throws Exception {
		
		//given
		long id     = 1;
		String code = "srte4t3453453";
		String name = "Spanish";
		Collection<Language> languages = List.of(new Language(id, code, name));
		
		//when
		Mockito.when(languageService.findAll()).thenReturn(languages);
		
		//execute
		mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
	}
	
	@DisplayName("Get employee by id")
	@Test
	public void getLanguageById() throws Exception {
		
		//given
		long id     = 1;
		String code = "srte4t3453453";
		String name = "Spanish";
		Language language = new Language(id, code, name);
		
		//when
		Mockito.when(languageService.findById(id)).thenReturn(language);
		
		//execute
		mockMvc.perform(get(url + "/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(id), Long.class))
			.andExpect(jsonPath("$.code", is(code)))
			.andExpect(jsonPath("$.name", is(name)));
	}
	
	@DisplayName("Add new language")
	@Test
	public void addNewLanguage() throws Exception {
		
		//given
		LanguageRequest languageRequest = new LanguageRequest("234sdf234", "Spanish");
		
		//when
		Mockito.doNothing().when(languageService).add(languageRequest);
	
		//execute
		mockMvc.perform(post(url)
							.content(new ObjectMapper().writeValueAsString(languageRequest))
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated());
	}
	
	@DisplayName("Update an language by id")
	@Test
	public void updateLanguageById() throws Exception {
		
		//given
		long id = 1;
		LanguageRequest employeeRequest = new LanguageRequest("234234sdf23", "Spanish");
		
		//when
		Mockito.doNothing().when(languageService).update(id, employeeRequest);
		
		//execute
		mockMvc.perform(put(url + "/{id}", id)
							.content(new ObjectMapper().writeValueAsString(employeeRequest))
							.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Language updated")));
	}
	
	@DisplayName("Delete an language by id")
	@Test
	public void deleteLanguageById() throws Exception {
		
		//given
		long id = 1;
		
		//when
		Mockito.doNothing().when(languageService).delete(id);
		
		//execute
		mockMvc.perform(delete(url + "/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Language deleted")));
	}
}
