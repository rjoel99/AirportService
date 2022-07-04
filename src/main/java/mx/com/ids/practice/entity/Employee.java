package mx.com.ids.practice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * 
 * @author joel.rubio
 *
 */
@Data
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String surname;
	
	@OneToOne
	private Country country;
}
