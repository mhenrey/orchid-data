package settings;

import java.io.File;

/**
 * 
 */

/**
 * @author mike
 *
 */
public class GeoSource extends Source {
	String districtName;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ("GeoSource contains: " + districtName.toString());
	}

	/**
	 * @param districtName
	 */
	public GeoSource(String districtName, File path) {
		super(path);
		this.districtName = districtName;
	}
	
	/**
	 * @param districtName
	 */
	public GeoSource(File path) {
		super(path);
	}
	
}
