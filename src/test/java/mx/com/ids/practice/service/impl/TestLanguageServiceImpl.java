package mx.com.ids.practice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.com.ids.practice.entity.Language;
import mx.com.ids.practice.model.LanguageRequest;
import mx.com.ids.practice.repository.LanguageRepository;

/**
 * 
 * @author joel.rubio
 *
 */
@ExtendWith(MockitoExtension.class)
public class TestLanguageServiceImpl {

	@Mock
	private LanguageRepository languageRepository;
	
	@Spy
	@InjectMocks
	private LanguageServiceImpl languageService;
	
	
	@DisplayName("Find all languages")
	@Test
	public void findAllLanguages() {
		
		//given
		List<Language> expectedLanguages = List.of(new Language("23423423", "Spanish"));
		Collection<Language> actualLanguages;
		
		//when
		Mockito.when(languageRepository.findAll()).thenReturn(expectedLanguages);
		
		//execute
		actualLanguages = languageService.findAll();
		
		//then
		assertEquals(expectedLanguages, actualLanguages);
	}
	
	@DisplayName("Find language by id")
	@Test
	public void findLanguageById() {
		
		//given
		long id = 1;
		Optional<Language> expectedLanguage = Optional.of(new Language(id, "23423423", "Spanish"));
		Language actualLanguage;
		
		//when
		Mockito.when(languageRepository.findById(id)).thenReturn(expectedLanguage);
		
		//execute
		actualLanguage = languageService.findById(id);
		
		//then
		assertEquals(expectedLanguage.get(), actualLanguage);
	}
	
	@DisplayName("Find language by name")
	@Test
	public void findLanguageByName() {
		
		//given
		long id     = 1;
		String name = "Spanish";
		Optional<Language> expectedLanguage = Optional.of(new Language(id, "23423423", name));
		Optional<Language> actualLanguage;
		
		//when
		Mockito.when(languageRepository.findByName(name)).thenReturn(expectedLanguage);
		
		//execute
		actualLanguage = languageService.findByName(name);
		
		//then
		assertEquals(expectedLanguage, actualLanguage);
	}
	
	@DisplayName("Throw exception when the language id doesn't exist")
	@Test
	public void throwExceptionIfIdDoesntExist() {
		
		//given
		long id = 0;
		
		//when
		Mockito.when(languageRepository.findById(id)).thenThrow(EntityNotFoundException.class);
		
		//then
		assertThrows(EntityNotFoundException.class, () -> languageService.findById(id));
	}
	
	@DisplayName("Add new language")
	@Test
	public void addNewLanguage() {
		
		//given
		Language language = new Language(1L, "324234234", "Spanish");
		
		//when
		Mockito.when(languageRepository.save(language)).thenReturn(language);
		
		//execute
		languageService.add(language);
		
		//then
		Mockito.verify(languageRepository, Mockito.times(1)).save(language);
	}
	
	@DisplayName("Add new language from request")
	@Test
	public void addNewLanguageFromRequest() {
		
		//given
		LanguageRequest languageRequest = new LanguageRequest("3423324", "Spanish");
		Language language = new Language(languageRequest.getCode(), languageRequest.getName());
		
		//when
		Mockito.when(languageRepository.save(language)).thenReturn(language);
		
		//execute
		languageService.addFromRequest(languageRequest);
		
		//then
		Mockito.verify(languageRepository, Mockito.times(1)).save(language);
	}
	
	
	@DisplayName("Update a language by id")
	@Test
	public void updateLanguageById() {
		
		//given
		long id = 12323;
		LanguageRequest languageRequest = new LanguageRequest("23423423", "French");
		Language language = new Language("23423423", "Spanish");
		
		//when
		Mockito.doReturn(language).when(languageService).findById(id);
		Mockito.when(languageRepository.save(language)).thenReturn(language);
		
		//execute
		languageService.update(id, languageRequest);
		
		//then
		Mockito.verify(languageRepository, Mockito.times(1)).save(language);
	}
	
	@DisplayName("Delete a language by id")
	@Test
	public void deleteCountryById() {
		
		//given
		long id = 1;
		Language language = new Language(id, "23423423", "Spanish");
		
		//when
		Mockito.doReturn(language).when(languageService).findById(id);
		Mockito.doNothing().when(languageRepository).delete(language);
		
		//execute
		languageService.delete(id);
		
		//then
		Mockito.verify(languageRepository, Mockito.times(1)).delete(language);		
	}
}
