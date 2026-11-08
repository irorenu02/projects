package Proj;
import java.util.*;
public class BaselineParkingSystem {
	private ParkingSlot[] slots; // Flat array - O(n) access
	private int totalSlots;
	private int availableSlots;

	public BaselineParkingSystem(int n) {
		totalSlots = n;
		availableSlots = n;
		slots = new ParkingSlot[n];
		for (int i = 0; i < n; i++) {
			slots[i] = new ParkingSlot(i, "A", 1, 10.0 + i * 0.5); // Simple layout
		}
	}

	// O(n) Linear scan to find first available slot
	public int findAndAllocateNearestSlot(int entryPoint) {
		for (int i = 0; i < totalSlots; i++) {
			if (slots[i].isAvailable()) {
				slots[i].allocate();
				availableSlots--;
				return i;
			}
		}
		return -1;
	}

	// O(n²) Bubble Sort
	public void displaySortedSlots() {
		ParkingSlot[] available = getAvailableSlotsArray();
		bubbleSort(available); // O(n²)
		System.out.println("\nAvailable Slots (Bubble Sorted by distance):");
		for (ParkingSlot slot : available) {
			if (slot != null) {
				System.out.printf("Slot %d (Floor %d, Dist %.1f)\n", 
						slot.getId(), slot.getFloor(), slot.getDistance());
			}
		}
	}

	// O(n²) Simple route - brute force path checking
	public String getSimpleRoute(int from, int to) {
		return "Entry -> Aisle" + (from/10 + 1) + " -> Slot " + to;
	}

	private ParkingSlot[] getAvailableSlotsArray() {
		int count = 0;
		for (ParkingSlot s : slots) if (s.isAvailable()) count++;

		ParkingSlot[] available = new ParkingSlot[count];
		int idx = 0;
		for (ParkingSlot s : slots) {
			if (s.isAvailable()) available[idx++] = s;
		}
		return available;
	}

	private void bubbleSort(ParkingSlot[] arr) {
		int n = arr.length;
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) {
				if (arr[j].getDistance() > arr[j+1].getDistance()) {
					ParkingSlot temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}

	public void occupySlot(int slotId) {
		if (slotId >= 0 && slotId < totalSlots && slots[slotId].isAvailable()) {
			slots[slotId].occupy();
			availableSlots--;
		}
	}

	public void freeSlot(int slotId) {
		if (slotId >= 0 && slotId < totalSlots && !slots[slotId].isAvailable()) {
			slots[slotId].free();
			availableSlots++;
		}
	}

	public void showStats() {
		System.out.printf("Total: %d, Available: %d, Occupied: %d\n", 
				totalSlots, availableSlots, totalSlots - availableSlots);
	}

	/** Benchmark-only: runs bubble sort silently. */
	public void bubbleSortBenchmark() {
		ParkingSlot[] available = getAvailableSlotsArray();
		bubbleSort(available);
	}
}
