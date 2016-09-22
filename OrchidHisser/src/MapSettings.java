import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MapSettings {

	SchoolSettings schoolSettings;
	PollutionSettings pollutionSettings;
	
	// constructor is private - use the factory instead
	private MapSettings(){		
	}
	
	/* (non-Javadoc)
	 * @see Settings#FromFile(java.io.File)
	 */
	public static MapSettings FromFile(File file) {
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
			
		return mapSettings;
	}

	/* (non-Javadoc)
	 * @see Settings#FromXMLDocument(Documentent)
	 */
	public MapSettings FromXMLDocument(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

}
