package School;

import java.util.Scanner;

public class LabAct3 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		//Problem 1
		System.out.println("Please enter your age: ");
		int age = scanner.nextInt();
		
		if(age >= 18) {
			System.out.println("You are eligible to vote.");

			
		//Problem 2
		System.out.println("Please enter a number: ");
		int number = scanner.nextInt();
		
		if (number % 2 == 0) {
			System.out.println("The number is even.");
		} else {
			System.out.println("The number is odd.");
			
			
		// Problem 3
			System.out.println("Please enter your age: ");
			int userAge = scanner.nextInt();

			if (userAge < 18) {
			    System.out.println("You are eligible for a Junior Membership.");
			} else {
			    if (userAge <= 60) {
			        System.out.println("You are eligible for a Regular Membership.");
			    } else {
			        System.out.println("You are eligible for a Senior Membership.");
			    }
			}
		
		
		}
			
			
		}
		}
		
	
	}
