package settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchoolSettings {

	/**
	 * @return the scoreSources
	 */
	public List<ScoreSource> getScoreSources() {
		return scoreSources;
	}

	/**
	 * @param scoreSources
	 *            the scoreSources to set
	 */
	public void setScoreSources(List<ScoreSource> scoreSources) {
		this.scoreSources = scoreSources;
	}

	/**
	 * @return the geoSources
	 */
	public List<GeoSource> getGeoSources() {
		return geoSources;
	}

	/**
	 * @param geoSources
	 *            the geoSources to set
	 */
	public void setGeoSources(List<GeoSource> geoSources) {
		this.geoSources = geoSources;
	}

	List<ScoreSource> scoreSources;
	List<GeoSource> geoSources;

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
		return ("SchoolSettings contains: " + scoreSources.size() + " scoreSource(s) and " + geoSources.size()
				+ " geoSource(s).");
	}
}
