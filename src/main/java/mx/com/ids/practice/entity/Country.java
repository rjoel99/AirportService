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

/**
 * 
 * @author joel.rubio
 *
 */
@Data
@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@JoinColumn(name = "airport_id")
	@OneToMany
	private List<Airport> airports;
}
