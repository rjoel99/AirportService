package mx.com.ids.practice.service;

import java.util.Collection;

import mx.com.ids.practice.entity.Language;

public interface LanguageService {

	Collection<Language> findAll();
	Language findById(long id);
	void delete(long id);
	void update(long id, Language language);
}
