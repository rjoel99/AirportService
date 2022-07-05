package mx.com.ids.practice.service;

import java.util.Collection;
import java.util.List;

import mx.com.ids.practice.entity.Language;
import mx.com.ids.practice.model.LanguageRequest;

public interface LanguageService {

	void add(LanguageRequest languageRequest);
	Collection<Language> findAll();
	Language findById(long id);
	void delete(long id);
	void update(long id, LanguageRequest languageRequest);
	List<Language> fromIdsToEntities(List<Integer> languageIds);
}
