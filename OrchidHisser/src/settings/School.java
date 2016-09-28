/**
 * 
 */
package settings;

import java.util.List;

/**
 * @author mike
 *
 */
public class School {
	double rank;
	String name;
	List<Coordinate> coordinates;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "School [rank=" + rank + ", name=" + name + ", coordinates=" + coordinates.toString() + "]";
	}
	
}
