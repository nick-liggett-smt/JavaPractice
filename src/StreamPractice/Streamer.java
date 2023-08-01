package StreamPractice;

import java.io.*;

public class Streamer {
	/**
	 * Member Variable that holds the path to the desired folder as a String.
	 */
	private String rootFolder;

	/**
	 * Constructor that accepts a root folder name as a String.
	 * 
	 * @param rootFolderPath
	 */
	public Streamer(String rootFolderPath) {
		this.rootFolder = rootFolderPath;
	}

	/**
	 * Method to read the desired file.
	 * 
	 * @param fileName
	 * @return BufferedReader || null
	 */
	public BufferedReader readFile(String fileName) {
		try {
			FileReader fileReader = new FileReader(this.rootFolder + "/" + fileName);
			return new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Method to write to a file.
	 * 
	 * @param newFileName
	 * @return BufferedWriter || null
	 */
	public BufferedWriter writeFile(String newFileName) {
		try {
			FileWriter fileWriter = new FileWriter(this.rootFolder + "/" + newFileName);
			return new BufferedWriter(fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
