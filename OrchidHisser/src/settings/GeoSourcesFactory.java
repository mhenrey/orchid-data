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
public class GeoSourcesFactory extends SettingsFactory {

	/**
	 * @param geoSourcesNList
	 * @return
	 * @throws Exception
	 */
	public static List<GeoSource> fromElement(Element geoSourcesElement) throws Exception {

		List<Element> geoSourceElements = getElementList(geoSourcesElement, "GeoSource");

		List<GeoSource> geoSources = new ArrayList<GeoSource>();

		for (Element geoSourceElement : geoSourceElements) {
			geoSources.add(GeoSourceFactory.fromElement(geoSourceElement));
		}

		return geoSources;
	}

}
