package firstJavaPractice;

public class Dog {
	private String name = "Dash";
	private int age = 3;
	private String breed = "Fox-dog";

	void bark() {
		System.out.println("Bow-wow-wow!");
	}

	int getAge() {
		System.out.println(age);
		return age;
	}

	String getName() {
		System.out.println(name);
		return name;
	}

	String getBreed() {
		System.out.println(breed);
		return breed;
	}
}
