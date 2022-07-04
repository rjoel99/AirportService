package mx.com.ids.practice.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.ids.practice.entity.Airport;
import mx.com.ids.practice.service.AirportService;

/**
 * 
 * @author joel.rubio
 *
 */
@RestController
@RequestMapping("/airports")
public class AirportController {

	private AirportService airportService;

	public AirportController(AirportService airportService) {
		this.airportService = airportService;
	}
	
	@PostMapping
	public ResponseEntity<Object> add(@RequestBody Airport airport) {
		
		airportService.add(airport);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Airport created");
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Airport>> getAll() {
		
		Collection<Airport> airports = airportService.findAll();
	
		return ResponseEntity.ok(airports);
	}
	
	@GetMapping(path     = "/{id}",
			    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airport> getById(@PathVariable long id) {
		
		Airport airport = airportService.findById(id);
		
		return ResponseEntity.ok(airport);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateById(@PathVariable long id, @RequestBody Airport airport) {
		
		airportService.update(id, airport);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Airport updated");
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable long id) {
		
		airportService.delete(id);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Airport deleted");
		
		return ResponseEntity.ok(response);
	}
}
