package CollectionsPractice;

import java.util.*;

public class PeopleSorter {
	public static void main(String[] args) {

		// Create an ArrayList of folks (Person with ID, First Name, Last Name)

		// Instantiate folks
		Person person1 = new Person(1, "Nick", "Liggett");
		Person person2 = new Person(2, "Brian", "Knight");
		Person person3 = new Person(3, "Mary", "Turpin");
		Person person4 = new Person(4, "Alex", "Reiman");
		Person person5 = new Person(5, "Cole", "Stephenson");
		Person person6 = new Person(6, "Tom", "Fettig");

		// Create an ArrayList
		ArrayList<Person> listOfFolks = new ArrayList<>();

		// Put the folks into the ArrayList
		listOfFolks.add(person1);
		listOfFolks.add(person2);
		listOfFolks.add(person3);
		listOfFolks.add(person4);
		listOfFolks.add(person5);
		listOfFolks.add(person6);

		// Shuffle people into random order
		Collections.shuffle(listOfFolks);

		// Iterate over the array list and print data for each element
		listOfFolks.forEach((person) -> {
			System.out.println("First Name: " + person.getFirstName());
			System.out.println("Last Name: " + person.getLastName());
			System.out.println("ID: " + person.getId());
			System.out.println("----------------------");
		});

	}

}
