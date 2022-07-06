package mx.com.ids.practice.service;

import java.util.Collection;
import java.util.Optional;

import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.model.CountryRequest;

/**
 * 
 * @author joel.rubio
 *
 */
public interface CountryService {

	Collection<Country> findAll();
	Country findById(long id);
	Optional<Country> findByName(String name);
	Country add(Country country);
	void addFromRequest(CountryRequest country);
	void delete(long id);
	void update(long id, CountryRequest country);
}
