/**
 * 
 */
package geoData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import orchidHisser.OrchidHisser;

/**
 * @author mike
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolJSON {
	private static final Logger logger = LogManager.getLogger(OrchidHisser.class);
	private CRS crs;
	private List<Feature> features;
	private String name;

	private String type;

	/**
	 * 
	 */
	public void convertCRS() {

	}

	// correlate school scores with the schools
	public void correlateScoresWithSchools(String csvFile) {
		logger.trace("Correlating data.");

		ArrayList<String[]> scoreDataEntries = ReadScoreListCSVFile(csvFile);

		for (Feature elementarySchool : features) {
			String schoolName = elementarySchool.getProperties().getSimplifiedName();

			// set the rank to -1 (default, will indicate no data)
			elementarySchool.getProperties().setRank(-1.0);

			// search for a matching name
			for (String[] entry : scoreDataEntries) {
				String entryName = getSimplifiedName(entry[0]);
				// if a match is found, set the rank
				if (entryName.equalsIgnoreCase(schoolName)) {
					double rank = Double.parseDouble(entry[2]);
					elementarySchool.getProperties().setRank(rank);
					break;
				}
			}
		}
	}

	/**
	 * @return the crs
	 */
	public CRS getCrs() {
		return crs;
	}

	/**
	 * @return the features
	 */
	public List<Feature> getFeatures() {
		return features;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	// method to strip unnecessary characters from the school name
	public String getSimplifiedName(String name) {
		if (name.contains("Elem.")) {
			return name.replace(" Elem.", "");
		} else if (name.contains("Elementary")) {
			return name.replace(" Elementary", "");
		}
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param csvFile
	 * @return
	 */

	private ArrayList<String[]> ReadScoreListCSVFile(String csvFile) {
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

	/**
	 * @param crs
	 *            the crs to set
	 */
	public void setCrs(CRS crs) {
		this.crs = crs;
	}

	/**
	 * @param features
	 *            the features to set
	 */
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * 
	 */
	public static SchoolJSON convertProjectionToWGS84(SchoolJSON originalSchoolJSON) throws JsonGenerationException, JsonMappingException, IOException {
		// serialize to temp file
		ObjectMapper mapper = new ObjectMapper();
		try{
			File tempFilePath = new File("bin/temp/schoolJSON.json");
			tempFilePath.createNewFile();
			mapper.writeValue(tempFilePath, originalSchoolJSON);
		}
		catch (IOException e){
			logger.trace("Could not serialize.");
			return null;
		}
		catch (SecurityException e){
			logger.trace("Could not open file.");
			return null;
		}
		// call command to convert
		logger.trace("Generating converted projection.");
		executeCommand(
				"/usr/local/Cellar/gdal/1.11.5/bin/ogr2ogr -t_srs WGS84 -f geoJSON bin/temp/converted_schoolJSON.json bin/temp/schoolJSON.json");
		// deserialize
		SchoolJSON newSchoolJSON = mapper.readValue(new File("bin/temp/converted_schoolJSON.json"), SchoolJSON.class);
		return newSchoolJSON;
	}

}
