package mx.com.ids.practice.service.impl;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.ids.practice.entity.Employee;
import mx.com.ids.practice.repository.EmployeeRepository;
import mx.com.ids.practice.service.EmployeeService;

/**
 * 
 * @author joel.rubio
 *
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Collection<Employee> findAll() {
		
		log.info("Getting all employees..");
		
		Collection<Employee> employees = employeeRepository.findAll();
	
		log.info("Employees obtained");
		
		return employees;
	}

	@Override
	public Employee findById(long id) {
		
		log.info("Getting employee by id {}...", id);
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("The employee doesn't exist"));
		
		log.info("Employee with id {} obtained", id);
		
		return employee;
	}

	@Override
	public void delete(long id) {
		
		Employee employee = findById(id);
		
		log.info("Deleting employee with id {}...", id);
		
		employeeRepository.delete(employee);
		
		log.info("Employee with id {} deleted", id);
	}

	@Override
	public void update(long id, Employee employee) {
		
		Employee employeeSaved = findById(id);
		
		log.info("Updating employee with id {}...", id);
		
		employeeSaved.setFirstname(employee.getFirstname());
		employeeSaved.setSurname(employee.getSurname());
		
		employeeRepository.save(employeeSaved);
		
		log.info("Employee with id {} updated", id);
	}
}
