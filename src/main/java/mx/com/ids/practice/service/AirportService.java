package mx.com.ids.practice.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import mx.com.ids.practice.entity.Airport;

/**
 * 
 * @author joel.rubio
 *
 */
public interface AirportService {

	Collection<Airport> findAll();
	Airport findById(long id);
	Airport add(Airport airport);
	Optional<Airport> findByName(String name);
	void delete(long id);
	void update(long id, Airport airport);
	List<Airport> fromIdsToEntities(List<Integer> airportIds);
}
