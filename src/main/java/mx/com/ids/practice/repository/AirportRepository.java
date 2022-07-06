package mx.com.ids.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ids.practice.entity.Airport;

/**
 * 
 * @author joel.rubio
 *
 */
public interface AirportRepository extends JpaRepository<Airport, Long> {

	Optional<Airport> findByName(String name);
}
