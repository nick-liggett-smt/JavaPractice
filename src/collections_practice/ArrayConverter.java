package collections_practice;

import java.util.*;

public class ArrayConverter {

	public static void main(String[] args) {

		/**
		 * Create an Array of Strings
		 */
		String[] numsArray = { "one", "two", "three", "four", "five" };
		System.out.println("Array: " + Arrays.toString(numsArray));

		/**
		 * Convert the Array of Strings to a List
		 */
		List<String> numsList = Arrays.asList(numsArray);
		System.out.println("List: " + numsList);
	}

}