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
import mx.com.ids.practice.repository.AirportRepository;
import mx.com.ids.practice.repository.CountryRepository;
import mx.com.ids.practice.repository.EmployeeRepository;
import mx.com.ids.practice.repository.LanguageRepository;
import mx.com.ids.practice.service.AggregationService;

/**
 * 
 * @author joel.rubio
 *
 */
@Slf4j
@Service
@Transactional
public class AggregationServiceImpl implements AggregationService {

	private LanguageRepository languageRepository;
	private EmployeeRepository employeeRepository;
	private CountryRepository countryRepository;
	private AirportRepository airportRepository;
	
	public AggregationServiceImpl(LanguageRepository languageRepository, EmployeeRepository employeeRepository,
			CountryRepository countryRepository, AirportRepository airportRepository) {
		this.languageRepository = languageRepository;
		this.employeeRepository = employeeRepository;
		this.countryRepository = countryRepository;
		this.airportRepository = airportRepository;
	}

	@Override
	public void add(AggregationRequest clientRequest) {
	
		log.info("Adding new client...");
		
		Language language = addLanguage(clientRequest);
			
		Airport airport = addAirport(clientRequest);

		Country country = addCountry(clientRequest, List.of(airport));
		
		addEmployee(clientRequest, country, List.of(language));
		
		log.info("Client added");
	}
	
	public Language addLanguage(AggregationRequest aggregationRequest) {
		
		Optional<Language> languageOp = languageRepository.findByName(aggregationRequest.getLanguage());
		
		return languageOp.isPresent() ? languageOp.get()
									  : languageRepository.save(new Language(UUID.randomUUID().toString(), aggregationRequest.getLanguage()));
	}
	
	
	public Airport addAirport(AggregationRequest aggregationRequest) {
		
		Optional<Airport> airportOp = airportRepository.findByName(aggregationRequest.getAirport());
		
		return airportOp.isPresent() ? airportOp.get()
									 : airportRepository.save(new Airport(aggregationRequest.getAirport()));
	}
	
	public Country addCountry(AggregationRequest aggregationRequest, List<Airport> airports) {
		
		Optional<Country> countryOp = countryRepository.findByName(aggregationRequest.getCountry());
		
		return countryOp.isPresent() ? countryOp.get()
									 : countryRepository.save(new Country(UUID.randomUUID().toString(), aggregationRequest.getCountry(), airports));
	}
	
	public Employee addEmployee(AggregationRequest aggregationRequest, Country country, List<Language> languages) {
		
		Optional<Employee> employeeOp = employeeRepository.findByFirstnameAndSurname(aggregationRequest.getFirstname(), aggregationRequest.getSurname());
		
		return employeeOp.isPresent() ? employeeOp.get()
									  : employeeRepository.save(new Employee(aggregationRequest.getFirstname(), 
											  								 aggregationRequest.getSurname(),
																			 country,
																			 languages));
	}
}
