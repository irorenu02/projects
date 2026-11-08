package School;

import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		double balance = 1000.00;
		int choice;
		
		do {
			System.out.println("ATM Menu");
			System.out.println("1. Check Balance");
			System.out.println("2. Withdraw Money");
			System.out.println("3. Deposit Money");
			System.out.println("4. Exit");
			System.out.println("Enter you choice: ");
			choice = input.nextInt();
			
			switch (choice) {
			case 1:
				System.out.printf("Your balance is: $%.2f\n", balance);
				System.out.println("-------------------");
				break;
			
			case 2:
				System.out.println("Enter amount to withdraw:  $ ");
			double withdraw = 0; 																						//,9w = input.nextDouble();
				if (withdraw <= 0) {
					System.out.println("Invalid amount. Must be greater than 0.");
				} else if (withdraw > balance) {
					System.out.println("Insufficient balance.");
					System.out.println("-------------------");
				} else {
					balance -= withdraw;
					System.out.printf("Withdraw successful. New balance: $%.2f\n" , balance);
					System.out.println("-------------------");
				}
				break;
				
			case 3: 
				System.out.println("Enter amount to deposit: $");
				double deposit = input.nextDouble();
				if (deposit <= 0) {
					System.out.println("Invalid amount. Must be greater than 0.");
				} else {
					balance += deposit;
					System.out.printf("Withdraw successful. New balance: $%.2f\n", balance);
					System.out.println("-------------------");
					
				}
				break;
				
			case 4:
				System.out.println("Thank you for using our ATM.");
				break;
				
				default:
					System.out.println("Invalid option. Please try again.");

			} 
			
		} while (choice !=4);
		input.close();
	

	}

}
