package mx.com.ids.practice.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import mx.com.ids.practice.entity.Language;
import mx.com.ids.practice.model.LanguageRequest;

public interface LanguageService {

	Collection<Language> findAll();
	Language findById(long id);
	Optional<Language> findByName(String name);
	Language add(Language language);
	void addFromRequest(LanguageRequest languageRequest);
	void delete(long id);
	void update(long id, LanguageRequest languageRequest);
	List<Language> fromIdsToEntities(List<Integer> languageIds);
}
