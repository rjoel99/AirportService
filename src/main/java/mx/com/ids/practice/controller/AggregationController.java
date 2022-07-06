package mx.com.ids.practice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.ids.practice.model.AggregationRequest;
import mx.com.ids.practice.service.AggregationService;

/**
 * 
 * @author joel.rubio
 *
 */
@RestController
@RequestMapping("/apiv1/clientes/add")
public class AggregationController {

	private AggregationService aggregationService;
	
	public AggregationController(AggregationService aggregationService) {
		this.aggregationService = aggregationService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> add(@RequestBody AggregationRequest aggregationRequest) {
		
		aggregationService.add(aggregationRequest);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Record added");
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
}
