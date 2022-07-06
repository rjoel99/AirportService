package mx.com.ids.practice.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.ids.practice.entity.Language;
import mx.com.ids.practice.model.LanguageRequest;
import mx.com.ids.practice.repository.LanguageRepository;
import mx.com.ids.practice.service.LanguageService;

/**
 * 
 * @author joel.rubio
 *
 */
@Slf4j
@Service
public class LanguageServiceImpl implements LanguageService {

	private LanguageRepository languageRepository;
	
	public LanguageServiceImpl(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
	
	@Override
	public void add(LanguageRequest languageRequest) {
		
		log.info("Adding new language -> {}...", languageRequest.getName());
		
		Language language = new Language(languageRequest.getCode(), languageRequest.getName());
		
		languageRepository.save(language);
		
		log.info("Language {} added", languageRequest.getName());
	}
	
	@Override
	public List<Language> fromIdsToEntities(List<Integer> languageIds) {
		
		return languageIds.stream()
			.map(id -> findById(id))
			.collect(Collectors.toList());
	}

	@Override
	public Collection<Language> findAll() {
		
		log.info("Getting all languages..");
		
		Collection<Language> languages = languageRepository.findAll();
	
		log.info("Languages obtained");
		
		return languages;
	}

	@Override
	public Language findById(long id) {
		
		log.info("Getting language by id {}...", id);
		
		Language language = languageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("The language doesn't exist"));
		
		log.info("Language with id {} obtained", id);
		
		return language;
	}

	@Override
	public void delete(long id) {

		Language language = findById(id);
		
		log.info("Deleting language with id {}...", id);
		
		languageRepository.delete(language);
		
		log.info("Language with id {} deleted", id);
	}

	@Override
	public void update(long id, LanguageRequest languageRequest) {
		
		Language languageSaved = findById(id);
		
		log.info("Updating language with id {}...", id);
		
		languageSaved.setName(languageRequest.getName());
		languageSaved.setCode(languageRequest.getCode());
		
		languageRepository.save(languageSaved);
		
		log.info("Language with id {} updated", id);
	}
}
