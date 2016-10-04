import java.io.File;
import java.util.ArrayList;

import settings.GeoSource;
import settings.MapSettings;
import settings.MapSettingsFactory;
import settings.PollutionSettings;
import settings.SchoolSettings;
import settings.ScoreSource;

//Import log4j classes.
import org.apache.logging.log4j.Logger;

import geoData.SchoolJSON;

import org.apache.logging.log4j.LogManager;

public class OrchidHisser {

	// Define a static logger variable so that it references the
	// Logger instance named "OrchidHisser".
	private static final Logger logger = LogManager.getLogger(OrchidHisser.class);

	public static void main(String[] args) {
		logger.trace("Entering application.");

		boolean startupMode = false;
		MapSettings mapSettings = LoadSettings(startupMode);
		
		DownloadMapSources();
		ConcatenateMapSources(mapSettings);
		MakeTiles();
		CorrelateScoresWithSchools();
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
		}
		else{
			mapSettings = new MapSettings();
		
			PollutionSettings pollutionSettings = new PollutionSettings();
			SchoolSettings schoolSettings = new SchoolSettings();
		
			ArrayList<GeoSource> geoSources = new ArrayList<GeoSource>();
			ArrayList<ScoreSource> scoreSources = new ArrayList<ScoreSource>();
			
			geoSources.add(new GeoSource("Surrey", new File("GeoSources/surrey_elementary")));
			geoSources.add(new GeoSource("Vancouver", new File("GeoSources/vancouver_elementary")));
			schoolSettings.setGeoSources(geoSources);
		
			ArrayList<String> scoreSourceDistrictNames = new ArrayList<String>();
			scoreSourceDistrictNames.add("Surrey");
			scoreSourceDistrictNames.add("Vancouver");
			scoreSources.add(new ScoreSource(scoreSourceDistrictNames, new File("ScoreSources/Fraser2015")));
			
			schoolSettings.setScoreSources(scoreSources);
			
			mapSettings.setPollutionSettings(pollutionSettings);
			mapSettings.setSchoolSettings(schoolSettings);
		}
		return mapSettings;
	}

	// Download maps from online
	public static void DownloadMapSources() {
		logger.trace("Downloading map sources.");
	}

	// Merge map sources into one file
	public static void ConcatenateMapSources(MapSettings mapSettings) {
		logger.trace("Concatenating map sources.");
		
		// for each school in the school list, load into classes
		SchoolJSON school = new SchoolJSON();
		school.FromSettings(mapSettings.getSchoolSettings());
	}

	// make tiles from dataset
	public static void MakeTiles() {
		logger.trace("Generating tiles.");
	}

	// correlate school scores with the schools
	public static void CorrelateScoresWithSchools() {
		logger.trace("Correlating data.");
	}

}
