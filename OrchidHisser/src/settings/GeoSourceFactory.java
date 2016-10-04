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



		Element pathElement = getSingleElement(geoSourceElement, "Path");
		GeoSource geoSource = new GeoSource(PathFactory.fromElement(pathElement));
		
		geoSource.districtName = getStringValue("District", geoSourceElement);

		return geoSource;
	}
}
