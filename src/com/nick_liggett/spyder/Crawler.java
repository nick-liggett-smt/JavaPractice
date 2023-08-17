package com.nick_liggett.spyder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	/**
	 * This method should connect to the given homepageUrl and find all possible
	 * links stemming from it.
	 * 
	 * @param homepageUrl
	 * @param connection
	 * @return An ArrayList of all possible links.
	 */
	public ArrayList<URL> crawl(String webpageUrl) {
		
		/**
		 * Create an array list to hold links to be printed.
		 */
		ArrayList<URL> linkList = new ArrayList<>();

		/**
		 * Use JSoup to find all the links on the given URL
		 */
		try {
			Document document = Jsoup.connect(webpageUrl).get();
			Elements links = document.select("a");

			/**
			 * For each link, if it is a valid protocol and it is not already been added to
			 * linkList, add it to linkList as a URL
			 */
			for (Element link : links) {
				String linkUrl = link.attr("abs:href");
				if (linkUrl.startsWith("http") && !isUrlAlreadyPresent(linkList, linkUrl)) {
					linkList.add(new URL(linkUrl));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linkList;
	}

	/**
	 * Checks if a given URL is present within a given ArrayList
	 * 
	 * @param linkList
	 * @param url
	 * @return boolean
	 */
	private static boolean isUrlAlreadyPresent(ArrayList<URL> arrayList, String url) {
		for (URL link : arrayList) {
			if (link.toString().equals(url)) {
				return true;
			}
		}
		return false;
	}
}
