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
public class ScoreSourcesFactory extends SettingsFactory{

	/**
	 * @param scoreSourcesElement
	 * @return
	 * @throws Exception 
	 */
	public static List<ScoreSource> fromElement(Element scoreSourcesElement) throws Exception {
		
		List<Element> scoreSourceElements = getElementList(scoreSourcesElement, "ScoreSource");

		List<ScoreSource> scoreSources = new ArrayList<ScoreSource>();

		for (Element geoSourceElement : scoreSourceElements) {
			scoreSources.add(ScoreSourceFactory.fromElement(scoreSourcesElement));
		}

		return scoreSources;
	}

}
