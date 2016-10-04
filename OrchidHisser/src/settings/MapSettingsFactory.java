package settings;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author mike
 *
 */
public class MapSettingsFactory {

	private static final Logger logger = LogManager.getLogger(MapSettingsFactory.class);

	/**
	 * @param file
	 *            path to the map settings file
	 * @return settings class containing information to describe the map
	 * @throws Exception
	 */
	public static MapSettings FromFile(File file) throws Exception {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			logger.error(e1.getStackTrace().toString());
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(file);
		} catch (SAXException | IOException e) {
			logger.error(e.getStackTrace().toString());
		}
		doc.getDocumentElement().normalize();

		MapSettings mapSettings = new MapSettings(file);

		NodeList schoolSettingsNList = doc.getElementsByTagName("SchoolSettings");
		if (schoolSettingsNList.getLength() != 1) {
			String msg = "Expecting 1 school settings.";
			logger.error(msg);
			throw new Exception(msg);
		}
		Node schoolSettingsNode = schoolSettingsNList.item(0);
		Element schoolSettingsElement = (Element) schoolSettingsNode;

		mapSettings.schoolSettings = SchoolSettingsFactory.fromElement(schoolSettingsElement);

		logger.trace("Returning map settings:", mapSettings.toString());

		return mapSettings;
	}
}
