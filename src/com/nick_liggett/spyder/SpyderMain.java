package com.nick_liggett.spyder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.SSLSocket;

public class SpyderMain {

	/**
	 * Main method the the Spyder Program. Runs the web crawler.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String homepageUrl = "https://smt-stage.qa.siliconmtn.com";
		runSpyder(homepageUrl);
	}

	/**
	 * Uses the Crawler class to collect all links from the web page and store them
	 * in a list. Then use the ReaderWriter class to write out network requests,
	 * read the response, and write out the response to text files.
	 * 
	 * @param url
	 * @throws IOException 
	 */
	public static void runSpyder(String url) throws IOException {
		Crawler crawler = new Crawler();
		ReaderWriter readerWriter = new ReaderWriter();

		/**
		 * Collect all possible links using a Crawler class
		 */
		ArrayList<URL> links = crawler.crawl(url);

		/**
		 * Read the HTML from each link and write each page to a file using a
		 * ReaderWriter class
		 */

		readerWriter.writeLinksToFile(links);
		getAdminPage();
	}

	/**
	 * Handles the login and connection to the admin tool page
	 * @throws IOException
	 */
	public static void getAdminPage() throws IOException {
		String username = "nicholas.liggett@siliconmtn.com";
		String password = "Nick91890!";
		URL url = new URL("https://smt-stage.qa.siliconmtn.com/sb/admintool?cPage=stats&actionId=FLUSH_CACHE");
		/**
		 * Write a method to the readerWriter class that connects to a single web page
		 * and prints the HTML
		 */
		ReaderWriter readerWriter = new ReaderWriter();
		readerWriter.login(url, username, password);


	}

}
