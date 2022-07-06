package mx.com.ids.practice.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author joel.rubio
 *
 */
@Data
@NoArgsConstructor
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String surname;
	
	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@JsonManagedReference
	@ManyToMany
	@JoinTable(name               = "employee_language", 
			   joinColumns        = @JoinColumn(name = "employee_id"),
			   inverseJoinColumns = @JoinColumn(name = "language_id")
			   )
	private List<Language> languages;
	
	public Employee(String firstname, String surname, Country country, List<Language> languages) {
		
		this.firstname = firstname;
		this.surname = surname;
		this.country = country;
		this.languages = languages;
	}
	
	
	public Employee(Long id, String firstname, String surname, Country country, List<Language> languages) {
		this(firstname, surname, country, languages);
		this.id = id;
	}
	
	public void removeLanguage(Language language) {
		
		languages.remove(language);
		language.getEmployees().remove(this);
	}
}
