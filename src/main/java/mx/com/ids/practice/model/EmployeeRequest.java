package mx.com.ids.practice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author joel.rubio
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {

	private String firstname;
	private String surname;
	private int countryId;
	private List<Integer> languageIds;
}
