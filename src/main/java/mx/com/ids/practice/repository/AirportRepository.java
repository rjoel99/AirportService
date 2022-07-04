package mx.com.ids.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ids.practice.entity.Airport;

/**
 * 
 * @author joel.rubio
 *
 */
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
