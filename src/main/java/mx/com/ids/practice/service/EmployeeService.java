package mx.com.ids.practice.service;

import java.util.Collection;

import mx.com.ids.practice.entity.Employee;
import mx.com.ids.practice.model.EmployeeRequest;

/**
 * 
 * @author joel.rubio
 *
 */
public interface EmployeeService {

	void add(EmployeeRequest employeeRequest);
	Collection<Employee> findAll();
	Employee findById(long id);
	void delete(long id);
	void update(long id, EmployeeRequest employeeRequest);
}
