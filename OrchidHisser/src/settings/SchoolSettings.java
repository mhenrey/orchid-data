package settings;
import java.util.Arrays;
import java.util.List;

public class SchoolSettings {

	public List<ScoreSource> scoreSources;
	public List<GeoSource> geoSources;
		
	public SchoolSettings(){
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchoolSettings [scoreSources=" + (scoreSources.toString()) + ", geoSource="
				+ (geoSources.toString()) + "]";
	}
}


