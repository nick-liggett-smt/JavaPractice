package com.nick_liggett.spider;

import java.net.*;

import java.util.*;

import javax.net.ssl.HttpsURLConnection;

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderMain {

	/**
	 * First checks if an argument has been given, then runs the crawl method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Assert that an argument has been passed.
		 */
		if (args.length == 0) {
			System.out.println("Error: Please provide a full domain name as an argument.");
			return;
		}
		try {
			System.out.println("--- Initiating crawl over " + args[0] + " ---");

			crawl(args[0]);

			System.out.println("--- Crawl complete ---");

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void crawl(String initialUrl) throws IOException {
		// Create an array to hold links to be printed.
		ArrayList<URL> linkList = new ArrayList<>();

		// Convert the passed in domain name to a URL and add it to the list of links
		linkList.add(new URL(initialUrl));

		// Create a set to hold URL's that have already been added to linkList to handle
		// duplicate links.
		Set<URL> uniqueURLs = new HashSet<>();

		// Add the initial URL to the set of unique URLs so it is not duplicated.
		uniqueURLs.add(linkList.get(0));

		// Get the user's home directory
		String userHome = System.getProperty("user.home");

		// Create an index value for writing to multiple files within for loop.
		int index = 1;

		// *** For each URL in the linkList ***
		for (int i = 0; i < linkList.size(); i++) {

			// Specify the file path within the user's home directory
			String filePath = userHome + "/output" + index + ".txt";

			// Increment the index value so different files are written for each page.
			index++;

			// Create a file writer for writing out the html of the page to .txt files.
			FileWriter fileWriter = new FileWriter(filePath);

			// Connect to the current URL from linkList
			HttpsURLConnection connection = (HttpsURLConnection) linkList.get(i).openConnection();

			// Use a reader to read the input stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			// Scan the page for any links using jsoup.
			Document doc = Jsoup.connect(linkList.get(i).toString()).get();

			// Find all anchor (a) elements that have an "href" attribute
			Elements links = doc.select("a[href]");

			/**
			 * Iterate through the links found on the page. For each link, convert it to
			 * URL, then add only unique http links to the linkList
			 */
			for (Element link : links) {
				String linkUrl = link.attr("abs:href");
				try {
					if (linkUrl.startsWith("http")) {
						URL pageUrl = new URL(linkUrl);
						if (!uniqueURLs.contains(pageUrl)) {
							uniqueURLs.add(pageUrl);
							linkList.add(pageUrl);
						}
					}
				} catch (MalformedURLException e) {
					// Handle the exception if the URL is malformed
					e.printStackTrace();
				}
			}

			/**
			 * Once all links from the current page (excluding duplicates) are added to
			 * linkList, write out the html of the current page.
			 */
			System.out.println("Writing out html for: " + linkList.get(i));
			try {
				String line;
				while ((line = reader.readLine()) != null) {
					fileWriter.write(line);
					fileWriter.write("\n");
				}
				fileWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				fileWriter.close();
			}
			System.out.println("Write out complete.");
		}
	}
}
