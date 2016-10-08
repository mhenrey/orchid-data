/**
 * 
 */
package settings;

/**
 * @author mike
 *
 */
public class Coordinate {
	double latitude;
	double longitiude;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordinate [latitude=" + latitude + ", longitiude=" + longitiude + "]";
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitiude
	 */
	public double getLongitiude() {
		return longitiude;
	}
	/**
	 * @param longitiude the longitiude to set
	 */
	public void setLongitiude(double longitiude) {
		this.longitiude = longitiude;
	}
	
}
