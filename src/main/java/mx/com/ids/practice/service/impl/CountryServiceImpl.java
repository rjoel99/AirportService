package mx.com.ids.practice.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.com.ids.practice.entity.Airport;
import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.model.CountryRequest;
import mx.com.ids.practice.repository.CountryRepository;
import mx.com.ids.practice.service.AirportService;
import mx.com.ids.practice.service.CountryService;

/**
 * 
 * @author joel.rubio
 *
 */
@Slf4j
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	private CountryRepository countryRepository;
	private AirportService airportService;
	
	public CountryServiceImpl(CountryRepository countryRepository, AirportService airportService) {
		this.countryRepository = countryRepository;
		this.airportService = airportService;
	}

	
	@Override
	public Country add(Country country) {
		
		log.info("Adding new country -> {}...", country.getName());
		
		country.getAirports().forEach(a -> a.setCountry(country));
		
		Country countrySaved = countryRepository.save(country);
		
		log.info("Country {} added", country.getName());
		
		return countrySaved;
	}
	
	@Override
	public void addFromRequest(CountryRequest countryRequest) {
	
		log.info("Adding new country -> {}...", countryRequest.getName());
		
		List<Airport> airports = airportService.fromIdsToEntities(countryRequest.getAirportIds());
		
		Country country = new Country(countryRequest.getCode(), countryRequest.getName(), airports);
		
		airports.forEach(a -> a.setCountry(country));
		
		countryRepository.save(country);
		
		log.info("Country {} added", countryRequest.getName());
	}

	@Override
	public Collection<Country> findAll() {
		
		log.info("Getting all countries..");
		
		Collection<Country> countries = countryRepository.findAll();
	
		log.info("Countries obtained");
		
		return countries;
	}

	@Override
	public Country findById(long id) {

		log.info("Getting country by id {}...", id);
		
		Country country = countryRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new EntityNotFoundException("The country doesn't exist"));
		
		log.info("Country with id {} obtained", id);
		
		return country;
	}
	
	@Override
	public Optional<Country> findByName(String name) {
		
		log.info("Getting country by name {}...", name);
		
		Optional<Country> country = countryRepository.findByName(name);
		
		log.info("Country with name {} obtained", name);
		
		return country;
	}

	@Override
	public void delete(long id) {
		
		Country country = findById(id);
		
		log.info("Deleting country with id {}...", id);
		
		if (country.getEmployee() != null)
				country.getEmployee().setCountry(null);
		
		countryRepository.delete(country);
		
		log.info("Country with id {} deleted", id);
	}

	@Override
	public void update(long id, CountryRequest countryRequest) {
		
		Country countrySaved = findById(id);
		
		log.info("Updating country with id {}...", id);
		
		List<Airport> airports = airportService.fromIdsToEntities(countryRequest.getAirportIds());
		
		countrySaved.setName(countryRequest.getName());
		countrySaved.setCode(countryRequest.getCode());
				
		airports.forEach(a -> a.setCountry(countrySaved));
		countrySaved.getAirports().forEach(a -> a.setCountry(null));
		countrySaved.setAirports(airports);		
		
		countryRepository.save(countrySaved);
		
		log.info("Country with id {} updated", id);
	}
}
