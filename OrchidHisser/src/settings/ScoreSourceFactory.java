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



		Element pathElement = getSingleElement(scoreSourceElement, "Path");
		ScoreSource scoreSource = new ScoreSource(PathFactory.fromElement(pathElement));
		
		Element districtsElement = getSingleElement(scoreSourceElement, "Districts");

		List<Element> districtElements = getElementList(districtsElement, "District");

		for (Element districtElement : districtElements) { 
			String districtName = getStringValue("District", districtsElement);
			scoreSource.districtNames.add(districtName);
			
		}

		return scoreSource;
	}
}
