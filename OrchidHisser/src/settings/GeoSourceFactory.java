/**
 * 
 */
package settings;

import org.w3c.dom.Element;

/**
 * @author mike
 *
 */
public class GeoSourceFactory extends SettingsFactory {

	/**
	 * @param geoSourceElement
	 * @return
	 * @throws Exception
	 */
	public static GeoSource fromElement(Element geoSourceElement) throws Exception {

		GeoSource geoSource = new GeoSource();

		Element pathElement = getSingleElement(geoSourceElement, "Path");
		geoSource.path = PathFactory.fromElement(pathElement);

		Element districtElement = getSingleElement(geoSourceElement, "District");
		geoSource.district = DistrictFactory.fromElement(districtElement);

		return geoSource;
	}
}
