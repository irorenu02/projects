package Proj;
import java.util.Scanner;
public class BaselineMain {
	public static void main(String[] args) {
		System.out.println("=== BASELINE PARKING SYSTEM (Non-Optimized) ===");
		BaselineParkingSystem system = new BaselineParkingSystem(50); // 50 slots

		// Simulate some occupied slots
		system.occupySlot(5);
		system.occupySlot(12);
		system.occupySlot(25);
		system.occupySlot(35);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n1. Find and allocate nearest slot");
			System.out.println("2. Display all available slots (sorted)");
			System.out.println("3. Free a slot");
			System.out.println("4. Show stats");
			System.out.println("5. Exit");
			System.out.print("Choose: ");

			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				int allocatedSlot = system.findAndAllocateNearestSlot(0); // Entry point
				if (allocatedSlot != -1) {
					System.out.println("Allocated slot: " + allocatedSlot);
					System.out.println("Route: " + system.getSimpleRoute(0, allocatedSlot));
				} else {
					System.out.println("No available slots!");
				}
				break;
			case 2:
				system.displaySortedSlots();
				break;
			case 3:
				System.out.print("Enter slot to free: ");
				system.freeSlot(scanner.nextInt());
				break;
			case 4:
				system.showStats();
				break;
			case 5:
				System.exit(0);
			}
		}
	}
}