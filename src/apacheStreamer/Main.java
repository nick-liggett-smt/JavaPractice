package apacheStreamer;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class Main {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://192.168.1.70");
			URLConnection connection = url.openConnection();

			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					PrintWriter out = new PrintWriter(new FileWriter("/home/WebpageOutput.txt"))) {

				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					out.println(inputLine);
				}
				out.flush();
			}

			System.out.println("HTML content of the page has been written to WebpageOutput.txt");

		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}
}