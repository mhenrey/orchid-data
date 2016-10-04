package settings;

import java.io.File;

public class MapSettings {

	/**
	 * @return the schoolSettings
	 */
	public SchoolSettings getSchoolSettings() {
		return schoolSettings;
	}

	/**
	 * @param schoolSettings the schoolSettings to set
	 */
	public void setSchoolSettings(SchoolSettings schoolSettings) {
		this.schoolSettings = schoolSettings;
	}

	/**
	 * @return the pollutionSettings
	 */
	public PollutionSettings getPollutionSettings() {
		return pollutionSettings;
	}

	/**
	 * @param pollutionSettings the pollutionSettings to set
	 */
	public void setPollutionSettings(PollutionSettings pollutionSettings) {
		this.pollutionSettings = pollutionSettings;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	SchoolSettings schoolSettings;
	PollutionSettings pollutionSettings;
	File file;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MapSettings from: " + file + ".";
	}

	/**
	 * 
	 */
	public MapSettings(File file) {
		schoolSettings = new SchoolSettings();
		pollutionSettings = new PollutionSettings();
		this.file = file;
	}

	/**
	 * 
	 */
	public MapSettings() {
		schoolSettings = new SchoolSettings();
		pollutionSettings = new PollutionSettings();
		file = null;
	}

}
