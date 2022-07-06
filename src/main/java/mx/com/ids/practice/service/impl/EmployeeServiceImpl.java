package mx.com.ids.practice.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.entity.Employee;
import mx.com.ids.practice.entity.Language;
import mx.com.ids.practice.model.EmployeeRequest;
import mx.com.ids.practice.repository.EmployeeRepository;
import mx.com.ids.practice.service.CountryService;
import mx.com.ids.practice.service.EmployeeService;
import mx.com.ids.practice.service.LanguageService;

/**
 * 
 * @author joel.rubio
 *
 */
@Slf4j
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private CountryService countryService;
	private LanguageService languageService;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, CountryService countryService,
			LanguageService languageService) {
		this.employeeRepository = employeeRepository;
		this.countryService = countryService;
		this.languageService = languageService;
	}


	@Override
	public void add(EmployeeRequest employeeRequest) {
		
		log.info("Adding new employee {}...", employeeRequest.getFirstname());
		
		Country country = countryService.findById(employeeRequest.getCountryId());
		
		List<Language> languages = languageService.fromIdsToEntities(employeeRequest.getLanguageIds());
		
		Employee employee = new Employee(employeeRequest.getFirstname(), employeeRequest.getSurname(), country, languages);
		
		employeeRepository.save(employee);
		
		log.info("Employee {} added", employeeRequest.getFirstname());
	}
	

	@Override
	public Collection<Employee> findAll() {
		
		log.info("Getting all employees..");
		
		Collection<Employee> employees = employeeRepository.findAll();
	
		log.info("Employees obtained");
		
		return employees;
	}

	@Override
	public Employee findById(long id) {
		
		log.info("Getting employee by id {}...", id);
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("The employee doesn't exist"));
		
		log.info("Employee with id {} obtained", id);
		
		return employee;
	}

	@Override
	public void delete(long id) {
		
		Employee employee = findById(id);
		
		log.info("Deleting employee with id {}...", id);
		
		employeeRepository.delete(employee);
		
		log.info("Employee with id {} deleted", id);
	}

	@Override
	public void update(long id, EmployeeRequest employeeRequest) {
		
		Employee employeeSaved = findById(id);
		
		log.info("Updating employee with id {}...", id);
		
		employeeSaved.setFirstname(employeeRequest.getFirstname());
		employeeSaved.setSurname(employeeRequest.getSurname());
		employeeSaved.setCountry(countryService.findById(employeeRequest.getCountryId()));
		employeeSaved.setLanguages(languageService.fromIdsToEntities(employeeRequest.getLanguageIds()));
		
		employeeRepository.save(employeeSaved);
		
		log.info("Employee with id {} updated", id);
	}
}
