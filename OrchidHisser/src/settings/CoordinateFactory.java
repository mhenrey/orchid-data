/**
 * 
 */
package settings;

import java.util.List;

import org.w3c.dom.Element;

/**
 * @author mike
 *
 */
public class CoordinateFactory extends SettingsFactory{

	/**
	 * @param coordinatesElement
	 * @return
	 * @throws Exception 
	 */
	public static Coordinate fromElement(Element coordinateElement) throws Exception {
		Coordinate coordinate = new Coordinate();
		
		Element latitudeElement = getSingleElement(coordinateElement, "Latitude");
		coordinate.latitude = Double.parseDouble(latitudeElement.getNodeValue());

		Element longitudeElement = getSingleElement(coordinateElement, "Longitude");
		coordinate.longitiude = Double.parseDouble(longitudeElement.getNodeValue());
		
		return coordinate;
	}

	
}
