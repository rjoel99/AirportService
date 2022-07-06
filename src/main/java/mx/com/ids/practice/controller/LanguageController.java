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

import mx.com.ids.practice.entity.Language;
import mx.com.ids.practice.model.LanguageRequest;
import mx.com.ids.practice.service.LanguageService;

/**
 * 
 * @author joel.rubio
 *
 */
@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {

	private LanguageService languageService;

	public LanguageController(LanguageService languageService) {
		this.languageService = languageService;
	}
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Language>> getAll() {
		
		Collection<Language> languages = languageService.findAll();
	
		return ResponseEntity.ok(languages);
	}
	
	@GetMapping(path     = "/{id}",
			    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Language> getById(@PathVariable long id) {
		
		Language language = languageService.findById(id);
		
		return ResponseEntity.ok(language);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> add(@RequestBody LanguageRequest languageRequest) {
		
		languageService.addFromRequest(languageRequest);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Language created");
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	@PutMapping(path     = "/{id}",
			    consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateById(@PathVariable long id, @RequestBody LanguageRequest languageRequest) {
		
		languageService.update(id, languageRequest);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Language updated");
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable long id) {
		
		languageService.delete(id);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Language deleted");
		
		return ResponseEntity.ok(response);
	}
}
