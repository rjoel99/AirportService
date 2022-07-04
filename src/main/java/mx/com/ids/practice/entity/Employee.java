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
	private long id;
	
	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String surname;
	
	@JoinColumn(name = "country_id")
	@OneToOne
	private Country country;
	
	@JoinTable(name               = "employee_language", 
			   joinColumns        = @JoinColumn(name = "employee_id"),
			   inverseJoinColumns = @JoinColumn(name = "language_id")
			  )
	@ManyToMany
	private List<Language> languages;
	
	public Employee(String firstname, String surname, Country country, List<Language> languages) {
		
		this.firstname = firstname;
		this.surname = surname;
		this.country = country;
		this.languages = languages;
	}
}
