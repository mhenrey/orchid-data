/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import orchidHisser.OrchidHisser;

/**
 * @author mike
 *
 */
public class CompareOutput {

	/**
	 * Test method for {@link OrchidHisser#main(java.lang.String[])}.
	 */
	@Test
	public void testMain() {
		try {
			OrchidHisser.main(null);
		} catch (IOException e) {
			fail("Exception generated during operation.");
		}

		try {		
			File file1 = new File("bin/GeoSources/concatenated_elementary.json");
			File file2 = new File("bin/tests/concatenated_elementary.json");
			assertTrue("The files differ!", FileUtils.contentEquals(file1, file2));
		} catch (IOException e) {
			fail("Exception generated when comparing files.");
			e.printStackTrace();
		}		
	}

}
