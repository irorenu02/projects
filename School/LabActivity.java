package School;

import java.util.Scanner;

public class LabActivity {

	public static void main(String[] args) {
		// Create Scanner object to get input from user.
		Scanner input = new Scanner(System.in);
		
		//Ask user to put two numbers
		System.out.print("Enter the first number: ");
		int num1 = input.nextInt(); //Store as int
		
		System.out.print("Enter the second number: ");
		double num2 = input.nextDouble(); //Store as double
		
		//Put arithmetic operations
		double sum = num1 + num2; 				//Addition
		double difference = num1 - num2; 		//Subtraction
		double product = num1 * num2; 			//Multiplication
		double quotient = num1 / num2; 			//Division
		
		//Display results with proper order of precedence
		double complexExpression = (num1 + num2) * 2 / (num1 - 1);
		
		//Type casting
		int narrowed = (int) num2;
		double widened = num1;
		
		//String Concatenation
		String resultMessage = "Sum: " + sum + ", Difference: " + difference;
		
		//Results
		System.out.println("\n---RESULTS---");
		System.out.println("Sum: " + sum);
		System.out.println("Difference: " + difference);
		System.out.println("Product: " + product);
		System.out.println("quotient: " + quotient);
		System.out.println("Complex Expression Result: " + complexExpression);
		System.out.println("Narrowed: " + narrowed);
		System.out.println("Widened: " + widened);
		System.out.println("Concatenation Message: " + resultMessage);
		

		
		
	}

}
