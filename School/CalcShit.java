package School;
import java.util.*;

public class CalcShit {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter slope: ");
		double m = sc.nextDouble();
		System.out.print("Enter point x y: ");
		double x = sc.nextDouble();
		double y = sc.nextDouble();
		double b = y - m * x;
		System.out.println("y = " + m + "x + " + b);
		sc.close();
	
	}
}
