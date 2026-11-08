package DogPackage;

import java.util.Scanner;

public class App {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        System.out.println("SCHOOL CLUB MEMBER RECORD SYSTEM");
	        System.out.print("Enter array capacity: ");
	        int capacity = sc.nextInt();
	        sc.nextLine();
	    
	        Array memberList = new Array(capacity);
	        int option = 0;

	        while (option != 9) {
	            System.out.println("\nMENU");
	            System.out.println("1. Register member");
	            System.out.println("2. Delete member");
	            System.out.println("3. Search member");
	            System.out.println("4. Update member");
	            System.out.println("5. Display members");
	            System.out.println("6. Check if full");
	            System.out.println("7. Check if empty");
	            System.out.println("8. Show member count");
	            System.out.println("9. Exit");
	            System.out.print("Choose an option: ");
	            option = Integer.parseInt(sc.nextLine());

	            switch (option) {
	                case 1:
	                    System.out.print("\nEnter member name to register: ");
	                    String nameToAdd = sc.nextLine();
	                    if (memberList.insert(nameToAdd)) {
	                        System.out.println(nameToAdd + " was registered successfully.");
	                    }
	                    break;

	                case 2:
	                    System.out.print("\nEnter member name to delete: ");
	                    String nameToDelete = sc.nextLine();
	                    if (memberList.delete(nameToDelete)) {
	                        System.out.println(nameToDelete + " was deleted successfully.");
	                    } 
	                    break;

	                case 3:
	                    System.out.print("\nEnter member name to search: ");
	                    String nameToSearch = sc.nextLine();
	                    int index = memberList.search(nameToSearch);
	                    if (index != -1) {
	                        System.out.println(nameToSearch + " was found at index " + index + ".");
	                    }
	                    break;

	                case 4:
	                    System.out.print("\nEnter current member name: ");
	                    String oldName = sc.nextLine();
	                    System.out.print("Enter new member name: ");
	                    String newName = sc.nextLine();
	                    if (memberList.update(oldName, newName)) {
	                        System.out.println(oldName + " was updated to " + newName + ".");
	                    }
	                    break;

	                case 5:
	                    System.out.println();
	                    memberList.display();
	                    break;

	                case 6:
	                    System.out.println("\nMember list is full: " + memberList.isFull());
	                    break;

	                case 7:
	                    System.out.println("\nMember list is empty: " + memberList.isEmpty());
	                    break;

	                case 8: 
	                    System.out.println("\nCurrent number of members: " + memberList.getCount());
	                    break;

	                case 9:
	                    System.out.println("\nProgram ended.");
	                    break;

	                default:
	                    System.out.println("\nError: Invalid option. Please enter a number between 1 and 9.");
	            }
	        }

	        sc.close();
	    }
	}
	

