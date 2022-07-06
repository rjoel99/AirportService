package mx.com.ids.practice.model;

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
public class AggregationRequest {

	private String firstname;
	private String surname;
	private String country;
	private String language;
	private String airport;
}
