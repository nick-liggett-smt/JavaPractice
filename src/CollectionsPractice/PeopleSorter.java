package CollectionsPractice;

import java.util.*;

public class PeopleSorter {
	public static void main(String[] args) {

		/**
		 * Instantiate 6 Persons using the Person Class
		 */
		Person person1 = new Person(1, "Nick", "Liggett");
		Person person2 = new Person(2, "Brian", "Knight");
		Person person3 = new Person(3, "Mary", "Turpin");
		Person person4 = new Person(4, "Alex", "Reiman");
		Person person5 = new Person(5, "Cole", "Stephenson");
		Person person6 = new Person(6, "Tom", "Fettig");

		/**
		 * Declare an ArrayList and fill it with Persons
		 */
		ArrayList<Person> listOfFolks = new ArrayList<>();

		listOfFolks.add(person1);
		listOfFolks.add(person2);
		listOfFolks.add(person3);
		listOfFolks.add(person4);
		listOfFolks.add(person5);
		listOfFolks.add(person6);

		/**
		 * Use the shuffle method from the Collections Class to shuffle the ArrayList in
		 * a random order
		 */
		Collections.shuffle(listOfFolks);

		/**
		 * Iterate over the array list and print the data for each element
		 */
		listOfFolks.forEach((person) -> {
			System.out.println("First Name: " + person.getFirstName());
			System.out.println("Last Name: " + person.getLastName());
			System.out.println("ID: " + person.getId());
			System.out.println("----------------------");
		});

	}

}
