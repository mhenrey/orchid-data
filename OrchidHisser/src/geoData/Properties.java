/**
 * 
 */
package geoData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mike
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
	private String name;
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

    @JsonProperty("NAME")
    public void setName(String name) {
      this.name = name;
    }
    
    @JsonProperty("name")
    public void setLCName(String name) {
      this.name = name;
    }
}
