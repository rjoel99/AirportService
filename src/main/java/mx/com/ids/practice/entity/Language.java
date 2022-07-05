package mx.com.ids.practice.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@JsonBackReference
	@ManyToMany(mappedBy = "languages")
	private List<Employee> employees;
	
	public Language(String code, String name) {
		
		this.code = code;
		this.name = name;
	}
	
	@PreRemove
	public void removeEmployee() {
	
		employees.forEach(e -> e.getLanguages().remove(this));
	}
}
