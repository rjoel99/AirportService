package mx.com.ids.practice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<Airport> airports;
	
	public Country(String code, String name, List<Airport> airports) {
		this.code     = code;
		this.name     = name;
		this.airports = airports;
	}
}
