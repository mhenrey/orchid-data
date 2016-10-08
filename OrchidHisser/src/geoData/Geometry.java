/**
 * 
 */
package geoData;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author mike
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
	String type;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	

	ArrayList<float[][]> coordinates;

	/**
	 * @return the coordinates
	 */
	public ArrayList<float[][]> getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(ArrayList<float[][]> coordinates) {
		this.coordinates = coordinates;
	}
}
