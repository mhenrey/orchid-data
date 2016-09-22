import java.io.File;

import settings.MapSettings;
import settings.MapSettingsFactory;

//Import log4j classes.
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class OrchidHisser {

	// Define a static logger variable so that it references the
    // Logger instance named "OrchidHisser".
    private static final Logger logger = LogManager.getLogger(OrchidHisser.class);
    
	public static void main(String[] args) {
        logger.trace("Entering application.");

		File file = new File("src/Settings.xml");

		MapSettings mapSettings = null;
		try {
			mapSettings = MapSettingsFactory.FromFile(file);
		} catch (Exception e) {
	        logger.trace("Unable to load map settings.");
			e.printStackTrace();
		}
		DownloadMapSources();
		ConcatenateMapSources();
		MakeTiles();
		CorrelateScoresWithSchools();
	}

	// Download maps from online
	public static void DownloadMapSources(){
        logger.trace("Downloading map sources.");
	}

	// Merge map sources into one file
	public static void ConcatenateMapSources(){
        logger.trace("Concatenating map sources.");
	}

	// make tiles from dataset
	public static void MakeTiles(){
        logger.trace("Generating tiles.");
    }

	// correlate school scores with the schools
	public static void CorrelateScoresWithSchools(){
        logger.trace("Correlating data.");
	}

}
