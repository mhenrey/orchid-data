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
public class ScoreSourceFactory extends SettingsFactory {

	/**
	 * @param scoreSourceElement
	 * @return
	 * @throws Exception
	 */
	public static ScoreSource fromElement(Element scoreSourceElement) throws Exception {

		ScoreSource scoreSource = new ScoreSource();

		Element pathElement = getSingleElement(scoreSourceElement, "Path");
		scoreSource.path = PathFactory.fromElement(pathElement);

		List<Element> districtElements = getElementList(scoreSourceElement, "Districts");

		for (Element districtElement : districtElements) {
			scoreSource.districts.add(DistrictFactory.fromElement(districtElement));
		}

		return scoreSource;
	}
}
