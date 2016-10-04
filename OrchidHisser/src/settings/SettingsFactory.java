/**
 * 
 */
package settings;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mike
 *
 */
public abstract class SettingsFactory {

	protected static final Logger logger = LogManager.getLogger(SettingsFactory.class);

	protected static String getStringValue(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }
        return null;
    }
	
	protected static Element getSingleElement(Element rootElement, String elementName) throws Exception {

		NodeList nList = rootElement.getElementsByTagName(elementName);

		if (nList.getLength() != 1) {
			String msg = "Expecting 1 element with name {elementName}.";
			logger.error(msg);
			throw new Exception(msg);
		}
		return (Element) nList.item(0);
	}

	/**
	 * @param geoSourcesElement
	 * @param string
	 * @return
	 */
	protected static List<Element> getElementList(Element rootElement, String elementName) throws Exception {
		NodeList nList = rootElement.getElementsByTagName(elementName);

		if (nList.getLength() < 1) {
			String msg = "Expecting at least 1 element with name {elementName}.";
			logger.error(msg);
			throw new Exception(msg);
		}
		List<Element> elementList = new ArrayList<Element>();
		for (int i = 0; i < nList.getLength(); i++) {
			elementList.add((Element) nList.item(i));
		}

		return elementList;

	}
}
