package com.nick_liggett.spyder;

import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;
import javax.net.ssl.SSLSocket;

public class SpyderMain {

	public static void main(String[] args) throws IOException {
		String homepageUrl = "https://smt-stage.qa.siliconmtn.com";

		/**
		 * Create instances of Connection, Crawler, and ReaderWriter classes
		 */

		Connection connection = new Connection();
		Crawler crawler = new Crawler();
		ReaderWriter readerWriter = new ReaderWriter();

		/**
		 * Collect all possible links using a Crawler class
		 */

		ArrayList<URL> links = crawler.crawl(homepageUrl, connection);
		for (URL link : links)
			System.out.println(link);

		/**
		 * Read the HTML from each link and write each page to a file using a
		 * ReaderWriter class
		 */

		readerWriter.writeLinksToFile(links);
	}

}
