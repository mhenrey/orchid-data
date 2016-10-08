/**
 * 
 */
package geoData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author mike
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
	public String name;
	/**
	 * @return the name
	 */

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
