package mx.com.ids.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ids.practice.entity.Language;

/**
 * 
 * @author joel.rubio
 *
 */
public interface LanguageRepository extends JpaRepository<Language, Long> {

	Optional<Language> findByName(String name);
}
