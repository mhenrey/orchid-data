/**
 * 
 */
package settings;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

/**
 * @author mike
 *
 */
public class CoordinatesFactory extends SettingsFactory {

	/**
	 * @param coordinatesElement
	 * @return
	 * @throws Exception 
	 */
	public static List<Coordinate> fromElement(Element coordinatesElement) throws Exception {

		List<Element> coordinateElements = getElementList(coordinatesElement, "Coordinate");

		List<Coordinate> coordinates = new ArrayList<Coordinate>();

		for (Element coordinateElement : coordinateElements) {
			coordinates.add(CoordinateFactory.fromElement(coordinateElement));
		}

		return coordinates;

	}

}
