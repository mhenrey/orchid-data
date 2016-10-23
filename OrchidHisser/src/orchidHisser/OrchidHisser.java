package orchidHisser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import org.apache.logging.log4j.core.pattern.EqualsIgnoreCaseReplacementConverter;

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

	public static void main(String[] args) throws JsonParseException, IOException {
		logger.trace("Entering application.");

		boolean startupMode = false;
		MapSettings mapSettings = LoadSettings(startupMode);

		downloadMapSources();
		SchoolJSON concatenatedElementarySchoolJSON = concatenateMapSources(mapSettings);
		concatenatedElementarySchoolJSON.convertCRS();

		correlateScoresWithSchools(concatenatedElementarySchoolJSON);

		makeTiles(concatenatedElementarySchoolJSON);

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

	// Download maps from online
	public static void downloadMapSources() {
		logger.trace("Downloading map sources.");
	}

	// Merge map sources into one file
	public static SchoolJSON concatenateMapSources(MapSettings mapSettings) throws JsonParseException, IOException {
		logger.trace("Concatenating map sources.");

		// for each source file, load it into classes
		List<SchoolJSON> schoolJSONs = SchoolJSONFactory.FromSettings(mapSettings.getSchoolSettings());

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

	// make tiles from dataset
	public static void makeTiles(SchoolJSON concatenatedElementaryJSON) throws JsonGenerationException, JsonMappingException, IOException {
		
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

	// correlate school scores with the schools
	public static void correlateScoresWithSchools(SchoolJSON concatenatedElementarySchoolJSON) {
		logger.trace("Correlating data.");
		String csvFile = "bin/ScoreSources/Fraser2015.csv";
		ArrayList<String[]> scoreDataEntries = ReadScoreListCSVFile(csvFile);
		
		for(Feature elementarySchool:concatenatedElementarySchoolJSON.getFeatures()){
			String schoolName = elementarySchool.getProperties().getSimplifiedName();

			// set the rank to -1 (default, will indicate no data)
			elementarySchool.getProperties().setRank(-1.0);
			
			// search for a matching name
			for (String[] entry:scoreDataEntries){
				String entryName = getSimplifiedName(entry[0]);
				// if a match is found, set the rank
				if (entryName.equalsIgnoreCase(schoolName)){
					double rank = Double.parseDouble(entry[2]);
					elementarySchool.getProperties().setRank(rank);
					break;
				}
			}
		}
	}

	// method to strip unnecessary characters from the school name
    public static String getSimplifiedName(String name) {
        if (name.contains("Elem.")){
        	return name.replace(" Elem.", "");
        }
        else if (name.contains("Elementary")){
        	return name.replace(" Elementary", "");
        }
        return name;
      }
    
	/**
	 * @param csvFile
	 * @return
	 */
	
	private static ArrayList<String[]> ReadScoreListCSVFile(String csvFile) {
		// from
		// https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
		BufferedReader br = null;
		String line = "";
		String delimiter = ",";

		ArrayList<String[]> scoreDataEntries = new ArrayList<String[]>();

		try {

			br = new BufferedReader(new FileReader(csvFile));

			// get the first line containing the header
			String[] header = br.readLine().split(delimiter);

			// TODO validate header

			// now read the remaining lines
			while ((line = br.readLine()) != null) {
				// and store in a list
				scoreDataEntries.add(line.split(delimiter));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return scoreDataEntries;
	}

}
