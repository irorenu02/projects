package Dawg;

public class Appp {
	    public static void main(String[] args) {

	        LinkedList list = new LinkedList();

	        System.out.println("SCHOOL CLUB MEMBER RECORD SYSTEM");
	        System.out.println("Laboratory Activity 3: Linked List Implementation");

	        //initial state
	        System.out.println("Member list is empty: " + list.isEmpty());
	        System.out.println("Current number of members: " + list.getCount());
	        System.out.println();

	        //insert members
	        System.out.println("Register Ana: " + list.insert("Ana"));
	        System.out.println("Register Ben: " + list.insert("Ben"));
	        System.out.println("Register Carla: " + list.insert("Carla"));
	        System.out.println("Register Diego: " + list.insert("Diego"));
	        System.out.println("Register Ella: " + list.insert("Ella"));

	        //display list
	        list.display();
	        System.out.println("Current number of members: " + list.getCount());
	        System.out.println();

	        //search
	        System.out.println("Search Carla: " + list.search("Carla"));
	        System.out.println("Search Zoe: " + list.search("Zoe"));
	        System.out.println();

	        //update
	        System.out.println("Update Carla to Chloe: " + list.update("Carla", "Chloe"));
	        System.out.println("Update Mark to Miguel: " + list.update("Mark", "Miguel"));

	        list.display();
	        System.out.println();

	        //delete
	        System.out.println("Delete Ben: " + list.delete("Ben"));
	        System.out.println("Delete Noah: " + list.delete("Noah"));

	        list.display();

	        //final state
	        System.out.println("Member list is empty: " + list.isEmpty());
	        System.out.println("Current number of members: " + list.getCount());
	    }
	}


