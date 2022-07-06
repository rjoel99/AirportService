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
import mx.com.ids.practice.entity.Employee;
import mx.com.ids.practice.model.EmployeeRequest;
import mx.com.ids.practice.service.EmployeeService;

/**
 * 
 * @author joel.rubio
 *
 */
@WebMvcTest(controllers = EmployeeController.class)
public class TestEmployeeController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;
	
	private static String url;
	
	@BeforeAll
	public static void setUp() {
		url = "http://localhost:8080/api/v1/employees";
	}
	
	@DisplayName("Get all employees")
	@Test
	public void getAllEmployees() throws Exception {
		
		//given
		long id          = 1;
		String firstname = "srte4t3453453";
		String surname   = "Mexico";
		Collection<Employee> employees = List.of(new Employee(id, firstname, surname, null, null));
		
		//when
		Mockito.when(employeeService.findAll()).thenReturn(employees);
		
		//execute
		mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
	}
	
	@DisplayName("Get employee by id")
	@Test
	public void getEmployeeById() throws Exception {
		
		//given
		long id           = 1;
		String firstname  = "srte4t3453453";
		String surname    = "Mexico";
		Country country   = new Country(1L, "23423xdf4", "Mexico", null);
		Employee employee = new Employee(id, firstname, surname, country, null);
		
		//when
		Mockito.when(employeeService.findById(id)).thenReturn(employee);
		
		//execute
		mockMvc.perform(get(url + "/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(id), Long.class))
			.andExpect(jsonPath("$.firstname", is(firstname)))
			.andExpect(jsonPath("$.surname", is(surname)))
			.andExpect(jsonPath("$.country.id", is(employee.getCountry().getId()), Long.class))
			.andExpect(jsonPath("$.country.code", is(employee.getCountry().getCode())))
			.andExpect(jsonPath("$.country.name", is(employee.getCountry().getName())));
	}
	
	@DisplayName("Add new employee")
	@Test
	public void addNewEmployee() throws Exception {
		
		//given
		EmployeeRequest employeeRequest = new EmployeeRequest("John", "Doe", 2, List.of(1, 2, 3));
		
		//when
		Mockito.doNothing().when(employeeService).add(employeeRequest);
	
		//execute
		mockMvc.perform(post(url)
							.content(new ObjectMapper().writeValueAsString(employeeRequest))
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isCreated());
	}
	
	@DisplayName("Update an employee by id")
	@Test
	public void updateEmployeeById() throws Exception {
		
		//given
		long id = 1;
		EmployeeRequest employeeRequest = new EmployeeRequest("John", "Doe", 2, List.of(1, 2, 3));
		
		//when
		Mockito.doNothing().when(employeeService).update(id, employeeRequest);
		
		//execute
		mockMvc.perform(put(url + "/{id}", id)
							.content(new ObjectMapper().writeValueAsString(employeeRequest))
							.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Employee updated")));
	}
	
	@DisplayName("Delete an employee by id")
	@Test
	public void deleteEmployeeById() throws Exception {
		
		//given
		long id = 1;
		
		//when
		Mockito.doNothing().when(employeeService).delete(id);
		
		//execute
		mockMvc.perform(delete(url + "/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Employee deleted")));
	}
}
