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

import mx.com.ids.practice.entity.Country;
import mx.com.ids.practice.model.CountryRequest;
import mx.com.ids.practice.service.CountryService;

/**
 * 
 * @author joel.rubio
 *
 */
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

	private CountryService countryService;

	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Country>> getAll() {
		
		Collection<Country> countries = countryService.findAll();
	
		return ResponseEntity.ok(countries);
	}
	
	@GetMapping(path     = "/{id}",
			    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> getById(@PathVariable long id) {
		
		Country country = countryService.findById(id);
		
		return ResponseEntity.ok(country);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> add(@RequestBody CountryRequest countryRequest) {
		
		countryService.add(countryRequest);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Country created");
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	@PutMapping(path     = "/{id}",
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateById(@PathVariable long id, @RequestBody CountryRequest countryRequest) {
		
		countryService.update(id, countryRequest);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Country updated");
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable long id) {
		
		countryService.delete(id);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Country deleted");
		
		return ResponseEntity.ok(response);
	}
}
