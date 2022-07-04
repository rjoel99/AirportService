package mx.com.ids.practice.service;

import java.util.Collection;

import mx.com.ids.practice.entity.Airport;

/**
 * 
 * @author joel.rubio
 *
 */
public interface AirportService {

	Collection<Airport> findAll();
	Airport findById(long id);
	void delete(long id);
	void update(long id, Airport airport);
}
