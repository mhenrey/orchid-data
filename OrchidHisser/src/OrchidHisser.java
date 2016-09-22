import java.io.File;

public class OrchidHisser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");

		File file = new File("Settings.xml");

		MapSettings settings = MapSettings.FromFile(file);
		DownloadMapSources();
		ConcatenateMapSources();
		MakeTiles();
		CorrelateScoresWithSchools();
	}

	// Download maps from online
	public static void DownloadMapSources(){
		// TODO
	}

	// Merge map sources into one file
	public static void ConcatenateMapSources(){
		// TODO
	}

	// make tiles from dataset
	public static void MakeTiles(){
		// TODO
	}

	// correlate school scores with the schools
	public static void CorrelateScoresWithSchools(){
		// TODO
	}

}
