package DogPackage;

import javax.swing.JOptionPane;

public class UICalculator {

	public static void main(String[] args) {
		String[] options = {"Addition","Subtraction","Multiplication","Division"};

		int n = JOptionPane.showOptionDialog(null,
				"Choose the Arithmetic Operation:",
				"Calculator", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);

		switch(n) {

		case 0: {
			System.out.println("Addition");
			double num1 = Double.parseDouble (JOptionPane.showInputDialog("Enter First Number:"));
			double num2 = Double.parseDouble (JOptionPane.showInputDialog("Enter Second Number:"));
			JOptionPane.showMessageDialog(null, "The sum is " + Addition(num1, num2));
			break;

		} case 1: {
	
			System.out.println("Subtraction ");
			double num1 = Double.parseDouble
					(JOptionPane.showInputDialog("Enter First Number:"));
			double num2 = Double.parseDouble
					(JOptionPane.showInputDialog("Enter Second Number:"));
			JOptionPane.showMessageDialog(null, "The difference is " + Subtraction(num1, num2));
			break;

		} case 2: {
			System.out.println("Multiplication"); 
			double num1 = Double.parseDouble
					(JOptionPane.showInputDialog("Enter First Number:"));
			double num2 = Double.parseDouble
					(JOptionPane.showInputDialog("Enter Second Number:"));
			JOptionPane.showMessageDialog(null, "The product is " + Multiplication(num1, num2));
			break;

		} case 3: {
			System.out.println("Division");
			double num1 = Double.parseDouble
					(JOptionPane.showInputDialog("Enter First Number:"));
			double num2 = Double.parseDouble
					(JOptionPane.showInputDialog("Enter Second Number:"));
			JOptionPane.showMessageDialog(null, "The quotient is " + Division(num1, num2));
			break;
		}
	}
}

	public static double Addition(double a, double b) {
		double sum = a + b;
		return sum;
	}
	
	public static double Subtraction(double a, double b)
{
		
		double difference = a - b;

		return difference;
}
	
	public static double Multiplication(double a, double z)
{
		double product = a*z;
		return product;
}
	
	public static double Division(double a , double m) 
{
		
		double quotient = a/m;
		return quotient;
}
}