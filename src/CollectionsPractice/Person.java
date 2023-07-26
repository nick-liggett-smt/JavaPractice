package CollectionsPractice;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	
	/**
	 * Constructor for the Person class
	 * @param id
	 * @param firstName
	 * @param lastName
	 */
	public Person(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Getter method for id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter method for firstName
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Getter method for getLastName
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
}
