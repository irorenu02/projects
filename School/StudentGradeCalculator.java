package School;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.*;

public class StudentGradeCalculator {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("STUDENT GRADE CALCULATOR");
		System.out.println("------------------------------");
		System.out.println("Enter student name: ");
		String name = s.nextLine();
		
		System.out.println("Enter student grade: ");
		int score = s.nextInt();
		
		String grades;
		if (score >= 0 && score <= 100);
		
		if (score >= 99 && score <= 100) {
			grades = "A";
		} else if (score >= 80 && score <= 89) {
			grades = "B";
		} else if (score >= 70 && score <= 79) {
			grades = "C";
		} else if (score >= 60 && score <= 69 ) {
			grades = "D";
		} else {
			grades = "F";
		}
		
		System.out.println("\nRESULTS");
		System.out.println("Name: " + name);
		System.out.println("Score: " + score);
		System.out.println("Grade: " + grades);
		
		try {
			FileWriter writer = new FileWriter("C:\\Users\\User\\eclipse-workspace\\Projects\\src\\projects\\Grades.txt", true);
			writer.write("Name: " + name + "\n");
			writer.write("Score: " + score + "\n");
			writer.write("Grade: " + grades + "\n");
			writer.write("---------------------------");
			writer.close();
			System.out.println("Results are saved to Grades.txt");
		}catch (IOException e) {
			System.out.println("An error occured");

		}
		s.close();
		
		
		
		

	}

}
