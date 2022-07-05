package mx.com.ids.practice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

/**
 * 
 * @author joel.rubio
 *
 */
@Data
@Entity
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@JsonBackReference
	@JoinColumn(name = "country_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Country country;
}
