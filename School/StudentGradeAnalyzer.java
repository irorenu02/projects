package School;
import java.util.*;
import java.util.Scanner;

public class StudentGradeAnalyzer {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		String[] names = new String [100];
		int[] grades = new int [100];
		int studentCount = 0;
		
		while (true) {
			System.out.println("\n---Student Grade Analyzer---");
			System.out.println("1. Enter student grade ");
			System.out.println("2. Display all grades ");
			System.out.println("3. Show statistics ");
			System.out.println("Enter your choice: ");
			
			int choice = s.nextInt();
			switch (choice) {
			case 1: 
				System.out.println("How many students: ");
				studentCount = s.nextInt();
				s.nextLine();
				
			for (int i = 0; i < studentCount; i++) {
				System.out.println("Enter name S" + (i + 1) + ": ");
				names [i] = s.nextLine();
				System.out.println("Enter grade S" + (i + 1) + ": ");
				grades [i] = s.nextInt(); 
				s.nextLine();
			}
			break;
			
			case 2:
				System.out.println("\n--- All grades ---");
				System.out.println("Name \tGrade");
				for (int i = 0; i < studentCount; i++) {
					System.out.println(names[i] + "\t" + grades [i]);
					
				}
				break;
			
			case 3:
				if (studentCount == 0) {
					System.out.println("No student data available.");
					break;
				}
				int highest = grades[0];
				int lowest = grades[0];
				int total = 0;
				int passed = 0;

				for (int i = 0; i < studentCount; i++) {
					if (grades[i] > highest)
						highest = grades[i];
					if (grades[i] < lowest)
						lowest = grades[i];
					if (grades[i] >= 75)
						passed++;
					total += grades [i];
				}
				double average = (double)
						total / studentCount;
				int failed = studentCount - passed;
				System.out.println("\n=== Statistics ===");
				System.out.println("Highest Grade: " + highest);
				System.out.println("lowest Grade: " + lowest);
				System.out.println("Average " + average);
				System.out.println("Passed " + passed);
				System.out.println("Failed " + failed);
				break;
			case 4:
				System.out.println("Exiting program... ");
				s.close();
				return;
			default:
				System.out.println("Invalid choice, try again. ");
			
				
			}
		}

	}

}
