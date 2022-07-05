package mx.com.ids.practice.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	
	@JoinColumn(name = "country_id")
	@OneToMany
	private List<Airport> airports;
	
	public Country(String code, String name, List<Airport> airports) {
		this.code     = code;
		this.name     = name;
		this.airports = airports;
	}
}
