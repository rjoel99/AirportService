package mx.com.ids.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ids.practice.entity.Employee;

/**
 * 
 * @author joel.rubio
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByFirstnameAndSurname(String firstname, String surname);
}
