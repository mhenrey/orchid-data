package settings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */

/**
 * @author mike
 *
 */
public class SchoolSettingsFactory extends SettingsFactory {

	// private static final Logger logger =
	// LogManager.getLogger(SchoolSettingsFactory.class);

	/**
	 * @param schoolSettingsNode
	 * @return
	 * @throws Exception
	 */
	public static SchoolSettings fromElement(Element schoolSettingsElement) throws Exception {
		SchoolSettings schoolSettings = new SchoolSettings();

		Element geoSourcesElement = getSingleElement(schoolSettingsElement, "GeoSources");
		schoolSettings.geoSources = GeoSourcesFactory.fromElement(geoSourcesElement);

		Element scoreSourcesElement = getSingleElement(schoolSettingsElement, "ScoreSources");
		schoolSettings.scoreSources = ScoreSourcesFactory.fromElement(scoreSourcesElement);

		return schoolSettings;
	}

}
