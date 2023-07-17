package ArrayPractice;

import java.util.Arrays;

public class ArrayDemo {
	public static void main(String[] args) {
	
		// Declare an array of ten elements
		int[] randomNumbers = new int[10];	
		
		// Fill the array with random numbers using randVal
		for (int i = 0; i < randomNumbers.length; i++) {
			randomNumbers[i] = (int)(Math.random() * 100);
			
			int[] numberPair = {i, randomNumbers[i]};
			
			// Print each element and it's index position
			System.out.println(Arrays.toString(numberPair));
		}	
    }
}	