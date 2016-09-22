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
public class SchoolSettingsFactory {

	private static final Logger logger = LogManager.getLogger(SchoolSettingsFactory.class);

	/**
	 * @param schoolSettingsNode
	 * @return
	 * @throws Exception 
	 */
	public static SchoolSettings fromElement(Element schoolSettingsElement) throws Exception {
		SchoolSettings schoolSettings = new SchoolSettings();
		
		NodeList geoSourcesNList = schoolSettingsElement.getElementsByTagName("geoSources");
		if (geoSourcesNList.getLength() > 1){
			String msg = "Expecting 1 geo sources.";
			logger.error(msg);
			throw new Exception(msg);
		}
		Node geoSourcesNode = geoSourcesNList.item(0);
		Element geoSourcesElement = (Element) geoSourcesNode;				

		schoolSettings.geoSources = GeoSourcesFactory.fromElement(geoSourcesElement);
		
		NodeList scoreSourcesNList = schoolSettingsElement.getElementsByTagName("scoreSources");
		if (scoreSourcesNList.getLength() > 1){
			String msg = "Expecting 1 score sources.";
			logger.error(msg);
			throw new Exception(msg);
		}
		Node scoreSourcesNode = scoreSourcesNList.item(0);
		Element scoreSourcesElement = (Element) scoreSourcesNode;
		
		schoolSettings.scoreSources = ScoreSourcesFactory.fromElement(scoreSourcesElement);
		
		
		return schoolSettings;
	}

}
