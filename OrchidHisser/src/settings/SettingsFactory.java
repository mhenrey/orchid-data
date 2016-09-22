/**
 * 
 */
package settings;

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
	
	public static Element getSingleElement(Element rootElement, String elementName) throws Exception{
		
		NodeList nList = rootElement.getElementsByTagName(elementName);
		
		if (nList.getLength() != 1){
			String msg = "Expecting 1 element with name {elementName}.";
			logger.error(msg);
			throw new Exception(msg);
		}
		return (Element) nList.item(0);
	}
}
