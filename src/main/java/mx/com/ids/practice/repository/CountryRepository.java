package mx.com.ids.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ids.practice.entity.Country;

/**
 * 
 * @author joel.rubio
 *
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

}
