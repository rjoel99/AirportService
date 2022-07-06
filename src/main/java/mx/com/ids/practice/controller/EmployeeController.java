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

import mx.com.ids.practice.entity.Employee;
import mx.com.ids.practice.model.EmployeeRequest;
import mx.com.ids.practice.service.EmployeeService;

/**
 * 
 * @author joel.rubio
 *
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getAll() {
		
		Collection<Employee> employees = employeeService.findAll();
	
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping(path     = "/{id}",
			    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getById(@PathVariable long id) {
		
		Employee employee = employeeService.findById(id);
		
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> add(@RequestBody EmployeeRequest employeeRequest) {
		
		employeeService.add(employeeRequest);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Employee created");
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	@PutMapping(path     = "/{id}",
				consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateById(@PathVariable long id, @RequestBody EmployeeRequest employeeRequest) {
		
		employeeService.update(id, employeeRequest);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Employee updated");
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable long id) {
		
		employeeService.delete(id);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Employee deleted");
		
		return ResponseEntity.ok(response);
	}
}
