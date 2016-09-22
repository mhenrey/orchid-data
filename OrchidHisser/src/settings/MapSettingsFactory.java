package settings;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author mike
 *
 */
public class MapSettingsFactory {

	/**
	 * @param file path to the map settings file
	 * @return settings class containing information to decribe the map
	 * @throws Exception 
	 */
	public static MapSettings FromFile(File file) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(file);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        doc.getDocumentElement().normalize();


		MapSettings mapSettings = new MapSettings();

		NodeList schoolSettingsNList = doc.getElementsByTagName("SchoolSettings");

		if (schoolSettingsNList.getLength() > 1){
			throw new Exception("Expecting 1 school settings.");
		}			
		Node schoolSettingsNode = schoolSettingsNList.item(0);
		
		mapSettings.schoolSettings = SchoolSettingsFactory.FromNode(schoolSettingsNode);
		
		
		return mapSettings;
	}
}
