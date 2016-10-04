package settings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author mike
 *
 */
public class ScoreSource extends Source {

	List<String> districtNames;

	/**
	 * @param districts
	 */
	public ScoreSource(File path) {
		super(path);
		districtNames = new ArrayList<String>();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScoreSource contains: " + districtNames.size() + " districts.";
	}

	/**
	 * @param districtNames
	 */
	public ScoreSource(List<String> districtNames, File path) {
		super(path);
		this.districtNames = districtNames;
	}
	
}
