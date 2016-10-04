/**
 * 
 */
package geoData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import settings.GeoSource;
import settings.SchoolSettings;

/**
 * @author mike
 *
 */
public class SchoolJSONFactory {
	/**
	 * @param schoolSettings
	 * @throws IOException 
	 * @throws JsonParseException 
	 */
	public List<SchoolJSON> FromSettings(SchoolSettings schoolSettings) throws JsonParseException, IOException {
		// go through each school in the settings and load it
		List<GeoSource> geoSources = schoolSettings.getGeoSources();
		GeoSource geoSource = geoSources.get(0);
		File path = geoSource.getPath();
		
		List<SchoolJSON> schoolJSONs = new ArrayList<SchoolJSON>();
		if (path.exists()){
			ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
			SchoolJSON schoolJSON = mapper.readValue(path, SchoolJSON.class);
			schoolJSONs.add(schoolJSON);
		}
		
		return schoolJSONs;
	}
}
