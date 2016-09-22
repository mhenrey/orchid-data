package settings;
import java.util.Arrays;

public class SchoolSettings {

	public ScoreSource[] scoreSources;
	public GeoSource[] geoSources;
		
	public SchoolSettings(){
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchoolSettings [scoreSources=" + Arrays.toString(scoreSources) + ", geoSource="
				+ Arrays.toString(geoSources) + "]";
	}
}


