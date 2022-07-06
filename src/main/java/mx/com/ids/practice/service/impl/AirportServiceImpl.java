package mx.com.ids.practice.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public Airport add(Airport airport) {
		
		log.info("Adding new airport -> {}...", airport.getName());
		
		Airport airportSaved = airportRepository.save(airport);
		
		log.info("Aiport {} added", airport.getName());
		
		return airportSaved;
	}
	
	@Override
	public List<Airport> fromIdsToEntities(List<Integer> airportIds) {
		
		return airportIds.stream()
				.map(id -> findById(id))
				.collect(Collectors.toList());
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
	public Optional<Airport> findByName(String name) {
		
		log.info("Getting airport by name {}...", name);
		
		Optional<Airport> airport = airportRepository.findByName(name);
		
		log.info("Airport with name {} obtained", name);
		
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
