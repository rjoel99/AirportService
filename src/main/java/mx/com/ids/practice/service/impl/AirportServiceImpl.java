package mx.com.ids.practice.service.impl;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.ids.practice.entity.Airport;
import mx.com.ids.practice.repository.AirportRepository;
import mx.com.ids.practice.service.AirportService;

/**
 * 
 * @author joel.rubio
 *
 */
@Slf4j
@Service
public class AirportServiceImpl implements AirportService {

	private AirportRepository airportRepository;
	
	public AirportServiceImpl(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
	}

	@Override
	public Collection<Airport> findAll() {

		log.info("Getting all airports..");
		
		Collection<Airport> airports = airportRepository.findAll();
	
		log.info("Airports obtained");
		
		return airports;
	}

	@Override
	public Airport findById(long id) {
		
		log.info("Getting airport by id {}...", id);
		
		Airport airport = airportRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("The airport doesn't exist"));
		
		log.info("Airport with id {} obtained", id);
		
		return airport;
	}

	@Override
	public void delete(long id) {
	
		Airport airport = findById(id);
		
		log.info("Deleting airport with id {}...", id);
		
		airportRepository.delete(airport);
		
		log.info("Airport with id {} deleted", id);
	}

	@Override
	public void update(long id, Airport airport) {
		
		Airport airportSaved = findById(id);
		
		log.info("Updating airport with id {}...", id);
		
		airportSaved.setName(airport.getName());
		
		airportRepository.save(airportSaved);
		
		log.info("Airport with id {} updated", id);
	}

}
