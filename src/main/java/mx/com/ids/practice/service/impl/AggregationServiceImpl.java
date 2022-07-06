package mx.com.ids.practice.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.com.ids.practice.entity.Airport;
import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.entity.Employee;
import mx.com.ids.practice.entity.Language;
import mx.com.ids.practice.model.AggregationRequest;
import mx.com.ids.practice.service.AggregationService;
import mx.com.ids.practice.service.AirportService;
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
public class AggregationServiceImpl implements AggregationService {

	private LanguageService languageService;
	private EmployeeService employeeService;
	private CountryService countryService;
	private AirportService airportService;
	
	public AggregationServiceImpl(LanguageService languageService, EmployeeService employeeService,
			CountryService countryService, AirportService airportService) {
		super();
		this.languageService = languageService;
		this.employeeService = employeeService;
		this.countryService  = countryService;
		this.airportService  = airportService;
	}

	@Override
	public void add(AggregationRequest clientRequest) {
	
		log.info("Adding new record...");
		
		Language language = addLanguage(clientRequest);
			
		Airport airport = addAirport(clientRequest);

		Country country = addCountry(clientRequest, List.of(airport));
		
		addEmployee(clientRequest, country, List.of(language));
		
		log.info("Record added");
	}
	
	public Language addLanguage(AggregationRequest aggregationRequest) {
		
		Optional<Language> languageOp = languageService.findByName(aggregationRequest.getLanguage());
		
		return languageOp.isPresent() ? languageOp.get()
									  : languageService.add(new Language(UUID.randomUUID().toString(), aggregationRequest.getLanguage()));
	}
	
	
	public Airport addAirport(AggregationRequest aggregationRequest) {
		
		Optional<Airport> airportOp = airportService.findByName(aggregationRequest.getAirport());
		
		return airportOp.isPresent() ? airportOp.get()
									 : airportService.add(new Airport(aggregationRequest.getAirport()));
	}
	
	public Country addCountry(AggregationRequest aggregationRequest, List<Airport> airports) {
		
		Optional<Country> countryOp = countryService.findByName(aggregationRequest.getCountry());
		
		return countryOp.isPresent() ? countryOp.get()
									 : countryService.add(new Country(UUID.randomUUID().toString(), aggregationRequest.getCountry(), airports));
	}
	
	public Employee addEmployee(AggregationRequest aggregationRequest, Country country, List<Language> languages) {
		
		Optional<Employee> employeeOp = employeeService.findByFirstnameAndSurname(aggregationRequest.getFirstname(), aggregationRequest.getSurname());
		
		return employeeOp.isPresent() ? employeeOp.get()
									  : employeeService.add(new Employee(aggregationRequest.getFirstname(), 
											  							 aggregationRequest.getSurname(),
																		 country,
																		 languages));
	}
}
