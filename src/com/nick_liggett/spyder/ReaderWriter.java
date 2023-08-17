package com.nick_liggett.spyder;

import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.SSLSocket;

import java.io.*;

public class ReaderWriter {

	/**
	 * Should iterate through the given ArrayList of URLs and for each one, it
	 * should write out a separate file to the user's home directory
	 * 
	 * @param links
	 */
	public void writeLinksToFile(ArrayList<URL> links) {

		String userHome = System.getProperty("user.home");
		String outputDirectoryPath = userHome + File.separator + "HTMLResponses";
		File outputDirectory = new File(outputDirectoryPath);
		outputDirectory.mkdirs();

		for (int i = 0; i < links.size(); i++) {
			URL url = links.get(i);
			String fileName = "response_" + i + ".txt";
			File outputFile = new File(outputDirectory, fileName);
			int port = url.getPort(); // Get the port number from the URL
			if (port == -1) {
			    port = url.getDefaultPort(); // Use the default port if not specified in the URL
			}

			try {
				Connection connection = new Connection();
				SSLSocket socket = connection.createSocket(url.getHost(), port);

				PrintWriter out = new PrintWriter(socket.getOutputStream());
				out.print("GET " + url.getPath() + " HTTP/1.1\r\n");
				out.print("Host: " + url.getHost() + "\r\n");
				out.print("Connection: close\r\n");
				out.print("\r\n");
				out.flush();

				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
					response.append(System.lineSeparator());
				}
				reader.close();

				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
				writer.write(response.toString());
				writer.close();

				System.out.println("Response from " + url + " written to " + outputFile.getAbsolutePath());

				socket.close();
			} catch (IOException e) {
				System.err.println("Error processing " + url + ": " + e.getMessage());
			}
		}
	}

}
