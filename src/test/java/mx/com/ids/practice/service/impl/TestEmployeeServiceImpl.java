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

import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.entity.Employee;
import mx.com.ids.practice.entity.Language;
import mx.com.ids.practice.model.EmployeeRequest;
import mx.com.ids.practice.repository.EmployeeRepository;
import mx.com.ids.practice.service.LanguageService;

/**
 * 
 * @author joel.rubio
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestEmployeeServiceImpl {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private CountryServiceImpl countryService;

	@Mock
	private LanguageService languageService;
	
	@Spy
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	
	@DisplayName("Add new employee")
	@Test
	public void addEmployee() {
		
		//given
		EmployeeRequest employeeRequest = new EmployeeRequest("Elliot", "Alderson", 2, List.of(1, 2, 3));
		Country country                 = new Country("234234", "Mexico", null);
		List<Language> languages        = List.of(new Language("234634", "Spanish"));
		
		Employee employee = new Employee(employeeRequest.getFirstname(), employeeRequest.getSurname(), country, languages);
		
		//when
		Mockito.when(countryService.findById(employeeRequest.getCountryId())).thenReturn(country);
		Mockito.when(languageService.fromIdsToEntities(employeeRequest.getLanguageIds())).thenReturn(languages);
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		
		//execute
		employeeService.add(employeeRequest);
		
		//then
		Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
	}
	
	@DisplayName("Find all employees")
	@Test
	public void findAllEmployees() {
		
		//given
		List<Employee> expectedEmployees = List.of(new Employee("Elliot", "Alderson", null, null));
		Collection<Employee> actualEmployees;
		
		//when
		Mockito.when(employeeRepository.findAll()).thenReturn(expectedEmployees);
		
		//execute
		actualEmployees = employeeService.findAll();
		
		//then
		assertEquals(expectedEmployees, actualEmployees);
	}
	
	@DisplayName("Find employee by id")
	@Test
	public void findEmployeeById() {
		
		//given
		long id = 2134234;
		Optional<Employee> expectedEmployee = Optional.of(new Employee("Elliot", "Alderson", null, null));
		Employee actualEmployee;
		
		//when
		Mockito.when(employeeRepository.findById(id)).thenReturn(expectedEmployee);
		
		//execute
		actualEmployee = employeeService.findById(id);
		
		//then
		assertEquals(expectedEmployee.get(), actualEmployee);
	}
	
	@DisplayName("Throw exception when the employee id doesn't exist")
	@Test
	public void throwExceptionIfIdDoesntExist() {
		
		//given
		long id = 0;
		
		//when
		Mockito.when(employeeRepository.findById(id)).thenThrow(EntityNotFoundException.class);
		
		//then
		assertThrows(EntityNotFoundException.class, () -> employeeService.findById(id));
	}
	
	@DisplayName("Delete a employee by id")
	@Test
	public void deleteEmployeeById() {
		
		//given
		long id = 132334;
		Employee employee = new Employee("Elliot", "Alderson", null, null);
		
		//when
		Mockito.doReturn(employee).when(employeeService).findById(id);
		Mockito.doNothing().when(employeeRepository).delete(employee);
		
		//execute
		employeeService.delete(id);
		
		//then
		Mockito.verify(employeeRepository, Mockito.times(1)).delete(employee);
		
	}
	
	@DisplayName("Update a employee by id")
	@Test
	public void updateEmployeeById() {
		
		//given
		long id = 12323;
		EmployeeRequest employeeRequest = new EmployeeRequest("Elliot", "Alderson", 232, List.of(1, 2, 3));
		Employee expectedEmployee       = new Employee("Elliot", "Alderson", null, null);
		
		//when
		Mockito.doReturn(expectedEmployee).when(employeeService).findById(id);
		Mockito.when(employeeRepository.save(expectedEmployee)).thenReturn(expectedEmployee);
		
		//execute
		employeeService.update(id, employeeRequest);
		
		//then
		Mockito.verify(employeeRepository, Mockito.times(1)).save(expectedEmployee);
	}
}
