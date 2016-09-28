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
public class DistrictFactory extends SettingsFactory{

	/**
	 * @param districtElement
	 * @return
	 * @throws Exception 
	 */
	public static District fromElement(Element districtElement) throws Exception {

		District district = new District();

		List<Element> schoolsElement = getElementList(districtElement, "Schools");
		
		for (Element schoolElement : schoolsElement) {
			district.schools.add(SchoolFactory.fromElement(schoolElement));
		}

		return district;
	}

}
