package mx.com.ids.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ids.practice.entity.Country;

/**
 * 
 * @author joel.rubio
 *
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

	Optional<Country> findByName(String name);
}
