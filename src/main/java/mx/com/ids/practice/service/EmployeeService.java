package mx.com.ids.practice.service;

import java.util.Collection;

import mx.com.ids.practice.entity.Employee;

/**
 * 
 * @author joel.rubio
 *
 */
public interface EmployeeService {

	Collection<Employee> findAll();
	Employee findById(long id);
	void delete(long id);
	void update(long id, Employee employee);
}
