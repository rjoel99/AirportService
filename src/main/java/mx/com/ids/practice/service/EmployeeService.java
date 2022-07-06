package mx.com.ids.practice.service;

import java.util.Collection;
import java.util.Optional;

import mx.com.ids.practice.entity.Employee;
import mx.com.ids.practice.model.EmployeeRequest;

/**
 * 
 * @author joel.rubio
 *
 */
public interface EmployeeService {

	Collection<Employee> findAll();
	Employee findById(long id);
	Optional<Employee> findByFirstnameAndSurname(String firstname, String surname);
	Employee add(Employee employee);
	void addFromRequest(EmployeeRequest employeeRequest);
	void delete(long id);
	void update(long id, EmployeeRequest employeeRequest);
}
