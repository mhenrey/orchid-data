package orchidHisser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import settings.GeoSource;
import settings.MapSettings;
import settings.MapSettingsFactory;
import settings.PollutionSettings;
import settings.SchoolSettings;
import settings.ScoreSource;

//Import log4j classes.
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import geoData.Feature;
import geoData.SchoolJSON;
import geoData.SchoolJSONFactory;

import org.apache.logging.log4j.LogManager;

public class OrchidHisser {

	// Define a static logger variable so that it references the
	// Logger instance named "OrchidHisser".
	private static final Logger logger = LogManager.getLogger(OrchidHisser.class);

	
	// Merge map sources into one file
	public static SchoolJSON concatenateMapSources(List<SchoolJSON> schoolJSONs) throws JsonParseException, IOException {
		logger.trace("Concatenating map sources.");

		// create a new JSON collection that will contain information from all
		// source files
		SchoolJSON concatenatedElementaryJSON = new SchoolJSON();

		// populate the heading information from the first JSON file element
		concatenatedElementaryJSON.setCrs(schoolJSONs.get(0).getCrs());
		concatenatedElementaryJSON.setName("Elementary Schools");
		concatenatedElementaryJSON.setType(schoolJSONs.get(0).getType());

		// populate the features list with all elements from all JSON sources
		ArrayList<Feature> concatenatedFeatureList = new ArrayList<Feature>();
		for (SchoolJSON schoolJSON : schoolJSONs) {
			for (Feature feature : schoolJSON.getFeatures()) {
				concatenatedFeatureList.add(feature);
			}
		}
		concatenatedElementaryJSON.setFeatures(concatenatedFeatureList);

		return concatenatedElementaryJSON;
	}

	// Download maps from online
	public static List<SchoolJSON> downloadMapSources(MapSettings mapSettings) throws JsonParseException, IOException {
		logger.trace("Downloading map sources.");
		// for each source file, load it into classes
		return SchoolJSONFactory.FromSettings(mapSettings.getSchoolSettings());
	}

	// from https://www.mkyong.com/java/how-to-execute-shell-command-from-java/
	private static String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

	/**
	 * @param startupMode
	 */
	private static MapSettings LoadSettings(boolean startupMode) {
		MapSettings mapSettings = null;
		if (startupMode) {
			File file = new File("src/Settings.xml");
			try {
				mapSettings = MapSettingsFactory.FromFile(file);
			} catch (Exception e) {
				logger.trace("Unable to load map settings.");
				e.printStackTrace();
			}
		} else {
			mapSettings = new MapSettings();

			PollutionSettings pollutionSettings = new PollutionSettings();
			SchoolSettings schoolSettings = new SchoolSettings();

			ArrayList<GeoSource> geoSources = new ArrayList<GeoSource>();
			ArrayList<ScoreSource> scoreSources = new ArrayList<ScoreSource>();

			geoSources.add(new GeoSource("Surrey", new File("GeoSources/surrey_elementary.json")));
			geoSources.add(new GeoSource("Vancouver", new File("GeoSources/vancouver_elementary.json")));
			geoSources.add(new GeoSource("North Vancouver", new File("GeoSources/northVancouver_elementary.json")));
			schoolSettings.setGeoSources(geoSources);

			ArrayList<String> scoreSourceDistrictNames = new ArrayList<String>();
			scoreSourceDistrictNames.add("Surrey");
			scoreSourceDistrictNames.add("Vancouver");
			scoreSourceDistrictNames.add("North Vancouver");
			scoreSources.add(new ScoreSource(scoreSourceDistrictNames, new File("ScoreSources/Fraser2015")));

			schoolSettings.setScoreSources(scoreSources);

			mapSettings.setPollutionSettings(pollutionSettings);
			mapSettings.setSchoolSettings(schoolSettings);
		}
		return mapSettings;
	}

	public static void main(String[] args) throws JsonParseException, IOException {
		logger.trace("Entering application.");

		boolean startupMode = false;
		MapSettings mapSettings = LoadSettings(startupMode);

		// obtain all the school information
		List<SchoolJSON> schoolJSONs = downloadMapSources(mapSettings);
		
		// convert all the projctions to the standard
		for (SchoolJSON elementarySchool:schoolJSONs){
			elementarySchool = SchoolJSON.convertProjectionToWGS84(elementarySchool);
		}
		
		// merge into one file
		SchoolJSON concatenatedElementarySchoolJSON = concatenateMapSources(schoolJSONs);

		// merge with the score information
		String csvFile = "bin/ScoreSources/Fraser2015.csv";
		concatenatedElementarySchoolJSON.correlateScoresWithSchools(csvFile);

		// make into mapbox tiles
		makeTiles(concatenatedElementarySchoolJSON);
	}

	// make tiles from dataset
	public static void makeTiles(SchoolJSON concatenatedElementaryJSON)
			throws JsonGenerationException, JsonMappingException, IOException {

		logger.trace("Generating tiles. Writing JSON to file.");
		// write the json to file
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("bin/GeoSources/concatenated_elementary.json"), concatenatedElementaryJSON);

		logger.trace("Generating converted projection.");
		executeCommand(
				"/usr/local/Cellar/gdal/1.11.5/bin/ogr2ogr -t_srs WGS84 -f geoJSON bin/GeoSources/converted_elementary.json bin/GeoSources/concatenated_elementary.json");
		logger.trace("Now making .mbtiles file.");
		executeCommand(
				"/usr/local/Cellar/tippecanoe/1.13.0/bin/tippecanoe -o bin/GeoSources/concatenated_elementary.mbtiles bin/GeoSources/converted_elementary.json -f");
	}

}
