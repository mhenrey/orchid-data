package settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchoolSettings {

	public List<ScoreSource> scoreSources;
	public List<GeoSource> geoSources;

	public SchoolSettings() {
		scoreSources = new ArrayList<ScoreSource>();
		geoSources = new ArrayList<GeoSource>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchoolSettings [scoreSources=" + (scoreSources.toString()) + ", geoSource=" + (geoSources.toString())
				+ "]";
	}
}
