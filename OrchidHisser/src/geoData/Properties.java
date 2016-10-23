/**
 * 
 */
package geoData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mike
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
	private String name;
	private double rank;

	/**
	 * @return the name
	 */

	@JsonProperty("SCHOOL_NAME")
	public void setSchoolName(String schoolName) {
		this.name = schoolName;
	}

	@JsonProperty("name")
	public String getLCName() {
		return name;
	}

	// method to strip unnecessary characters from the school name
	@JsonIgnore
	public String getSimplifiedName() {
		if (name.contains("Elem.")) {
			return name.replace(" Elem.", "");
		} else if (name.contains("Elementary")) {
			return name.replace(" Elementary", "");
		}
		return name;
	}

	@JsonProperty("rank")
	public double getRank() {
		return rank;
	}

	public void setRank(double rank) {
		this.rank = rank;
	}

	@JsonProperty("NAME")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("Primary_Name")
	public void setPrimaryName(String primaryName) {
		this.name = primaryName;
	}

	@JsonProperty("name")
	public void setLCName(String name) {
		this.name = name;
	}
}
