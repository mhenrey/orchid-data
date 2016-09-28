/**
 * 
 */
package settings;

import org.w3c.dom.Element;

/**
 * @author mike
 *
 */
public class SchoolFactory extends SettingsFactory{

	/**
	 * @param schoolElement
	 * @return
	 * @throws Exception 
	 */
	public static School fromElement(Element schoolElement) throws Exception {
		School school = new School();
		
		Element rankElement = getSingleElement(schoolElement, "Path");
		school.rank = Double.parseDouble(rankElement.getNodeValue());

		Element nameElement = getSingleElement(schoolElement, "District");
		school.name = nameElement.getNodeValue();
		
		Element coordinatesElement = getSingleElement(schoolElement, "Coordinates");
		school.coordinates = CoordinatesFactory.fromElement(coordinatesElement);
		
		return school;
	}

	
}
