package Proj;

import java.util.Scanner;

public class OptimizedMain {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║   OPTIMIZED PARKING SLOT ALLOCATION & NAVIGATION SYSTEM      ║");
        System.out.println("║   Graph + Min-Heap + Hash Table + Dijkstra + BFS + MergeSort ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        OptimizedParkingSystem system = new OptimizedParkingSystem(50);

        // Pre-occupy some slots to simulate a partially full lot
        system.occupySlot(5);
        system.occupySlot(12);
        system.occupySlot(25);
        system.occupySlot(35);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│           MAIN MENU             │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│ 1. Find & allocate nearest slot  │");
            System.out.println("│ 2. Get shortest route to slot    │");
            System.out.println("│ 3. Look up a slot by ID          │");
            System.out.println("│ 4. Display all available slots   │");
            System.out.println("│ 5. Free a slot                   │");
            System.out.println("│ 6. Show system stats             │");
            System.out.println("│ 7. Run performance benchmark     │");
            System.out.println("│ 8. Exit                          │");
            System.out.println("└─────────────────────────────────┘");
            System.out.print("Choose: ");

            int choice;
            try { choice = Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Invalid input."); continue; }

            switch (choice) {

                case 1: {
                    long t0 = System.nanoTime();
                    int slot = system.findAndAllocateNearestSlot();
                    long t1 = System.nanoTime();
                    if (slot == -1) {
                        System.out.println("No available slots!");
                    } else {
                        System.out.println("✔ Allocated slot: " + slot);
                        System.out.println("  Route: " + system.getShortestRoute(slot));
                        System.out.printf("  Time (BFS + Dijkstra): %.4f ms%n", (t1-t0)/1_000_000.0);
                    }
                    break;
                }

                case 2: {
                    System.out.print("Enter slot ID: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        long t0 = System.nanoTime();
                        String route = system.getShortestRoute(id);
                        long t1 = System.nanoTime();
                        System.out.println("  Route: " + route);
                        System.out.printf("  Dijkstra time: %.4f ms%n", (t1-t0)/1_000_000.0);
                    } catch (NumberFormatException e) { System.out.println("Invalid ID."); }
                    break;
                }

                case 3: {
                    System.out.print("Enter slot ID: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        ParkingSlot s = system.lookupSlot(id);
                        if (s == null) { System.out.println("Slot not found."); }
                        else {
                            System.out.printf("Slot %d | Type: %s | Floor: %d | Dist: %.1f m | Status: %s%n",
                                s.getId(), s.getType(), s.getFloor(), s.getDistance(),
                                s.isAvailable() ? "AVAILABLE" : "OCCUPIED");
                        }
                    } catch (NumberFormatException e) { System.out.println("Invalid ID."); }
                    break;
                }

                case 4: {
                    long t0 = System.nanoTime();
                    system.displaySortedSlots();
                    long t1 = System.nanoTime();
                    System.out.printf("  MergeSort display time: %.4f ms%n", (t1-t0)/1_000_000.0);
                    break;
                }

                case 5: {
                    System.out.print("Enter slot ID to free: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        system.freeSlot(id);
                    } catch (NumberFormatException e) { System.out.println("Invalid ID."); }
                    break;
                }

                case 6:
                    system.showStats();
                    break;

                case 7:
                    Benchmark.run();
                    break;

                case 8:
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose 1–8.");
            }
        }
    }
}
