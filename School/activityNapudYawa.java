package School;
import java.util.*;
public class activityNapudYawa {

	// public static boolean isEven(int number) {
		// if (number % 2 == 0) {
		// return true;
	// } else {
		// return false;
	// }
	// }
	
	// public static void main(String[] args) {
		// System.out.println(isEven(10));
		// System.out.println(isEven(15));
	// }
		

	    public static String greet(String name, int timeOfDay) {
	        String greeting;

	        switch (timeOfDay) {
	            case 1:
	                greeting = "Good Morning, " + name + "!";
	                break;
	            case 2:
	                greeting = "Good Afternoon, " + name + "!";
	                break;
	            case 3:
	                greeting = "Good Evening, " + name + "!";
	                break;
	            default:
	                greeting = "Good Day, " + name + "!";
	                break;
	        }

	        return greeting;
	    }

	    public static void main(String[] args) {
	        String result = greet("Juan", 3);
	        String result1 = greet("Julio", 2);
	        String result2 = greet("Julia", 1);
	        String result3 = greet("Jjajangmyeon", 0);
	        System.out.println(result);
	        System.out.println(result1);
	        System.out.println(result2);
	        System.out.println(result3);
	     
	    }
	}
		
	
	

	
