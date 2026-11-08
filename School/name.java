package School;

import java.util.Scanner;

public class name {

	public static void main(String[] args) {
		
		String firstName = "Joe";
		String lastName = "Wilson";
		int age = 16;
		
		System.out.println("Hi my name is " + firstName + " " + lastName + " " + "and i am " + age  +" years old.");
		
		//
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter score: ");
		int score = sc.nextInt();
		
		if (score < 75) {
			System.out.println("Better luck next time :((");
		} else if (score >= 75 && score <= 80) {
			System.out.println("Fair");
		} else if (score >= 81 && score <= 85) {
			System.out.println("Good");
		} else if (score >= 86 && score <= 90) {
			System.out.println("Great");
		} else if (score >= 91 && score <= 95) {
			System.out.println("Wow");
		} else if (score >= 96 && score <= 100) {
			System.out.println("wow graduate na daw sya");
		} else {
			System.out.println("pataka napud kag code");
		}
		
		//
		
		
			
		}

	}


