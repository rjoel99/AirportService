package mx.com.ids.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.ids.practice.entity.Language;

/**
 * 
 * @author joel.rubio
 *
 */
public interface LanguageRepository extends JpaRepository<Language, Long> {

}
