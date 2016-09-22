package settings;
import java.util.Arrays;

import org.w3c.dom.Node;

public class SchoolSettings {

	public ScoreSource[] scoreSources;
	public GeoSource[] geoSource;
		
	public SchoolSettings(){
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchoolSettings [scoreSources=" + Arrays.toString(scoreSources) + ", geoSource="
				+ Arrays.toString(geoSource) + "]";
	}
}


