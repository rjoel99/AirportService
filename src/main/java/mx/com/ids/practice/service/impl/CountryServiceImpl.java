package mx.com.ids.practice.service.impl;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.repository.CountryRepository;
import mx.com.ids.practice.service.CountryService;

/**
 * 
 * @author joel.rubio
 *
 */
@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

	private CountryRepository countryRepository;
	
	public CountryServiceImpl(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
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
		
		Country country = countryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("The country doesn't exist"));
		
		log.info("Country with id {} obtained", id);
		
		return country;
	}

	@Override
	public void delete(long id) {
		
		Country country = findById(id);
		
		log.info("Deleting country with id {}...", id);
		
		countryRepository.delete(country);
		
		log.info("Country with id {} deleted", id);
	}

	@Override
	public void update(long id, Country country) {
		
		Country countrySaved = findById(id);
		
		log.info("Updating country with id {}...", id);
		
		countrySaved.setName(country.getName());
		countrySaved.setCode(country.getCode());
		
		countryRepository.save(countrySaved);
		
		log.info("Country with id {} updated", id);
	}
}
