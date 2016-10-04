/**
 * 
 */
package settings;

import java.io.File;

/**
 * @author mike
 *
 */
public class Source {
	File path;

	/**
	 * @param path
	 */
	public Source(File path) {
		this.path = path;
	}

	/**
	 * @return the path
	 */
	public File getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(File path) {
		this.path = path;
	}
}
