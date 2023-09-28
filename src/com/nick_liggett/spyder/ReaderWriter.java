package com.nick_liggett.spyder;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import java.io.*;

public class ReaderWriter {

	/**
	 * Should iterate through the given ArrayList of URLs and for each one, it
	 * should write out a separate file to the user's home directory
	 * 
	 * @param links
	 */
	public void writeLinksToFile(ArrayList<URL> links) {

		// Setup a Folder called HTMLResponses for the file writer to write to
		String userHome = System.getProperty("user.home");
		String outputDirectoryPath = userHome + File.separator + "HTMLResponses";
		File outputDirectory = new File(outputDirectoryPath);
		outputDirectory.mkdirs();

		// Iterate through the list of links
		for (int i = 0; i < links.size(); i++) {
			URL url = links.get(i);
			String fileName = "response_" + (i + 1) + ".txt";
			File outputFile = new File(outputDirectory, fileName);
			int port = url.getPort(); // Get the port number from the URL
			if (port == -1) {
				port = url.getDefaultPort(); // Use the default port if not specified in the URL
			}
			// Use a socket output stream to connect to each link and write an HTTP
			try {
				Connection connection = new Connection();
				SSLSocket socket = connection.createSocket(url.getHost(), port);
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				out.print("GET " + url.getPath() + " HTTP/1.1\r\n");
				out.print("Host: " + url.getHost() + "\r\n");
				out.print("Connection: close\r\n");
				out.print("\r\n");
				out.flush();

				// Use a socket input stream to read the response
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
					response.append(System.lineSeparator());
				}
				reader.close();

				// Print the response to the designated directory
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

	/**
	 * Login to the admin tool page and print response to designated directory
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @throws MalformedURLException
	 */
	public void login(URL url, String username, String password) throws MalformedURLException {
		// Create an array list to hold cookies
		ArrayList<String> cookies = new ArrayList<String>();

		// Create the HTTP post request body string
		String postBody = "requestType=reqBuild&pmid=ADMIN_LOGIN&emailAddress=nicholas.liggett@siliconmtn.com&password=Nick91890!&l=";

		// Create the HTTP post request string
		String postRequest = "POST " + url.getPath() + " HTTP/1.1\r\n" + "Host: " + url.getHost() + ":" + 443 + "\r\n"
				+ "Content-Type: application/x-www-form-urlencoded\r\n" + "Content-Length: " + postBody.length()
				+ "\r\n" + "Connection: close\r\n" + "\r\n" + postBody;
		try {
			// Make an SSL Socket
			SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket socket = (SSLSocket) socketFactory.createSocket(url.getHost(), 443);

			// Use an output stream to send the HTTP post request
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			out.println(postRequest);
			out.println();
			out.flush();

			// Use an input stream to read the response
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				response.append(line).append("\n");
				if (line.contains("Set-Cookie")) {
					cookies.add(formatCookies(line));
				}
			}

			// Create an HTTP get request with the Cookie header using cookies stored in the
			// cookies array list
			String requestWithCookies = "GET " + "/sb/admintool?cPage=stats&actionId=FLUSH_CACHE" + " HTTP/1.1\r\n"
					+ "Host: " + url.getHost() + "\r\n" + "Cookie: " + cookies.get(0) + cookies.get(1) + cookies.get(2)
					+ cookies.get(3) + "\r\n" + "Connection: close\r\n" + "\r\n";

			// Make a new SSL Socket
			SSLSocketFactory socketFactory2 = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket socket2 = (SSLSocket) socketFactory2.createSocket(url.getHost(), 443);

			// Use an output stream to send the HTTP get request
			PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream())));
			output.println(requestWithCookies);
			output.println();
			output.flush();

			// Use an input stream to read the response
			BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
			StringBuilder response2 = new StringBuilder();
			String line2;
			while ((line2 = bufferedReader2.readLine()) != null) {
				response2.append(line2).append("\n");
			}
			
			// Designate directory for file writer to write to
			String userHome = System.getProperty("user.home");
			String outputDirectoryPath = userHome + File.separator + "HTMLResponses";
			File outputDirectory = new File(outputDirectoryPath);

			File outputFile = new File(outputDirectory, "response_9.txt");
			
			// Write response to designated file
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write(response2.toString());
			writer.close();
			System.out.println(
					"Response from https://smt-stage.qa.siliconmtn.com//sb/admintool?cPage=stats&actionId=FLUSH_CACHE written to /home/nick/HTMLResponses/response_9.txt");

			out.close();
			output.close();
			bufferedReader.close();
			bufferedReader2.close();
			socket.close();
			socket2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pulls cookies out of response header string
	 * @param line
	 * @return Just the cookie
	 */
	public String formatCookies(String line) {
		int startIndex = 12; // Start index of the substring
		int endIndex = line.indexOf(" ", startIndex); // End index of the substring

		// Check if the endIndex is valid and greater than the startIndex
		if (endIndex > startIndex) {
			// Return the modified substring
			return line.substring(startIndex, endIndex);
		} else {
			// Handle the case where there is no valid substring
			return "";
		}
	}

}
