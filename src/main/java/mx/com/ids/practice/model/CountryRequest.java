package mx.com.ids.practice.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author joel.rubio
 *
 */
@Getter
@Setter
public class CountryRequest {

	private String code;
	private String name;
	private List<Integer> airportIds;
}
