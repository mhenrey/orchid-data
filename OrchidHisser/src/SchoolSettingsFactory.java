import org.w3c.dom.Node;

/**
 * 
 */

/**
 * @author mike
 *
 */
public class SchoolSettingsFactory {

	/**
	 * @param schoolSettingsNode
	 * @return
	 */
	public static SchoolSettings FromNode(Node schoolSettingsNode) {
		SchoolSettings schoolSettings = new SchoolSettings();
		return schoolSettings;
	}

}
