package mx.com.ids.practice.service;

import java.util.Collection;

import mx.com.ids.practice.entity.Country;

/**
 * 
 * @author joel.rubio
 *
 */
public interface CountryService {

	Collection<Country> findAll();
	Country findById(long id);
	void delete(long id);
	void update(long id, Country country);
}
