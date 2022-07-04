package mx.com.ids.practice.service;

import java.util.Collection;
import java.util.List;

import mx.com.ids.practice.entity.Airport;

/**
 * 
 * @author joel.rubio
 *
 */
public interface AirportService {

	void add(Airport airport);
	Collection<Airport> findAll();
	Airport findById(long id);
	void delete(long id);
	void update(long id, Airport airport);
	List<Airport> fromIdsToEntities(List<Integer> airportIds);
}
