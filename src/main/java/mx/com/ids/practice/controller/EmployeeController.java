package mx.com.ids.practice.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.ids.practice.entity.Employee;
import mx.com.ids.practice.service.EmployeeService;

/**
 * 
 * @author joel.rubio
 *
 */
@RestController
@RequestMapping("/employees")
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
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateById(@PathVariable long id, @RequestBody Employee employee) {
		
		employeeService.update(id, employee);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Employee update");
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable long id) {
		
		employeeService.delete(id);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", "Employee update");
		
		return ResponseEntity.ok(response);
	}
}
