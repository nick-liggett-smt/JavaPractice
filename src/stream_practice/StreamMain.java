package stream_practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class StreamMain {
	public static void main(String[] args) throws IOException {

		/**
		 * Instantiate a new Streamer object
		 */
		Streamer streamer = new Streamer("/home/nick/Stream_Folder");

		/**
		 * Declare reader and writer variables that hold the return values of the
		 * streamer methods
		 */
		BufferedReader reader = streamer.readFile("Names.txt");
		BufferedWriter writer = streamer.writeFile("UpperCaseNames.txt");

		/**
		 * Read the each line of the file and write it to the new file upper cased
		 */
		String line;
		while ((line = reader.readLine()) != null) {
			writer.write(line.toUpperCase() + "\n");
		}
		reader.close();
		writer.close();
	}
}
