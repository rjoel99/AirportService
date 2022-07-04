package mx.com.ids.practice.service;

import java.util.Collection;
import java.util.List;

import mx.com.ids.practice.entity.Language;

public interface LanguageService {

	void add(Language language);
	Collection<Language> findAll();
	Language findById(long id);
	void delete(long id);
	void update(long id, Language language);
	List<Language> fromIdsToEntities(List<Integer> languageIds);
}
