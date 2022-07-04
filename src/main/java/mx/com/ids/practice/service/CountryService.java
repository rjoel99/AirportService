package mx.com.ids.practice.service;

import java.util.Collection;

import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.model.CountryRequest;

/**
 * 
 * @author joel.rubio
 *
 */
public interface CountryService {

	void add(CountryRequest country);
	Collection<Country> findAll();
	Country findById(long id);
	void delete(long id);
	void update(long id, CountryRequest country);
}
