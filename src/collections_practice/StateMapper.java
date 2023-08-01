package collections_practice;

import java.util.*;

public class StateMapper {

	public static void main(String[] args) {

		/**
		 * Create a HashMap called `stateMap` using the Map Class
		 */
		Map<String, String> stateMap = new HashMap<>();

		/**
		 * Insert pairs of state names and codes into the Map using put() method
		 */
		stateMap.put("AL", "Alabama");
		stateMap.put("AK", "Alaska");
		stateMap.put("AZ", "Arizona");
		stateMap.put("AR", "Arkansas");
		stateMap.put("CA", "California");
		stateMap.put("CO", "Colorado");
		stateMap.put("CT", "Connecticut");
		stateMap.put("DE", "Delaware");
		stateMap.put("FL", "Florida");
		stateMap.put("GA", "Georgia");
		stateMap.put("HI", "Hawaii");
		stateMap.put("ID", "Idaho");
		stateMap.put("IL", "Illinois");
		stateMap.put("IN", "Indiana");
		stateMap.put("IA", "Iowa");
		stateMap.put("KS", "Kansas");
		stateMap.put("KY", "Kentucky");
		stateMap.put("LA", "Louisiana");
		stateMap.put("ME", "Maine");
		stateMap.put("MD", "Maryland");
		stateMap.put("MA", "Massachusetts");
		stateMap.put("MI", "Michigan");
		stateMap.put("MN", "Minnesota");
		stateMap.put("MS", "Mississippi");
		stateMap.put("MO", "Missouri");
		stateMap.put("MT", "Montana");
		stateMap.put("NE", "Nebraska");
		stateMap.put("NV", "Nevada");
		stateMap.put("NH", "New Hampshire");
		stateMap.put("NJ", "New Jersey");
		stateMap.put("NM", "New Mexico");
		stateMap.put("NY", "New York");
		stateMap.put("NC", "North Carolina");
		stateMap.put("ND", "North Dakota");
		stateMap.put("OH", "Ohio");
		stateMap.put("OK", "Oklahoma");
		stateMap.put("OR", "Oregon");
		stateMap.put("PA", "Pennsylvania");
		stateMap.put("RI", "Rhode Island");
		stateMap.put("SC", "South Carolina");
		stateMap.put("SD", "South Dakota");
		stateMap.put("TN", "Tennessee");
		stateMap.put("TX", "Texas");
		stateMap.put("UT", "Utah");
		stateMap.put("VT", "Vermont");
		stateMap.put("VA", "Virginia");
		stateMap.put("WA", "Washington");
		stateMap.put("WV", "West Virginia");
		stateMap.put("WI", "Wisconsin");
		stateMap.put("WY", "Wyoming");

		/**
		 * Print the stateMap
		 */
		System.out.println("State Map: " + stateMap);
		System.out.println("");

		/**
		 * Order the stateMap by converting it to a TreeMap, a Class that implements the
		 * NavigableMap sub-interface of the SortedMap interface
		 */
		NavigableMap<String, String> sortedStateMap = new TreeMap<>(stateMap);
		System.out.println("Navigable State Map: " + sortedStateMap);
		System.out.println("");
		System.out.println("Reverse it: " + sortedStateMap.descendingMap());

	}
}
