package Proj;

/**
 * Optimized Parking Slot Allocation and Navigation System.
 *
 * Components (all implemented from scratch):
 *   - ParkingGraph   : adjacency-list weighted graph
 *   - HashTable      : O(1) avg slot lookup by id
 *   - MinHeap        : O(log n) proximity-based slot allocation
 *   - Dijkstra       : O((V+E) log V) shortest path navigation
 *   - BFS            : O(V+E) nearest available slot search
 *   - MergeSort      : O(n log n) stable slot display sorting
 */
public class OptimizedParkingSystem {

    private final ParkingGraph  graph;
    private final HashTable     table;
    private final MinHeap       heap;
    private final int           totalSlots;
    private final int           entryNode;
    private int                 availableCount;

    /**
     * Build system with a linear facility layout.
     * Nodes 0..n-1 are slots; node 0 is the entry point.
     * Each slot connects to the next with a fixed aisle distance.
     */
    public OptimizedParkingSystem(int numSlots) {
        this.totalSlots     = numSlots;
        this.entryNode      = 0;
        this.availableCount = numSlots;

        graph = new ParkingGraph(numSlots);
        table = new HashTable(numSlots * 2);
        heap  = new MinHeap(numSlots);

        // Build graph: linear chain with cross-aisle connections
        for (int i = 0; i < numSlots - 1; i++) {
            double dist = 5.0 + (i % 10) * 0.5; // vary distances slightly
            graph.addUndirectedEdge(i, i + 1, dist);
        }
        // Add some cross-connections to make routing non-trivial
        for (int i = 0; i + 10 < numSlots; i += 10) {
            graph.addUndirectedEdge(i, i + 10, 15.0);
        }

        // Populate hash table and min-heap with all slots
        for (int i = 0; i < numSlots; i++) {
            double distFromEntry = 10.0 + i * 0.5;
            ParkingSlot slot = new ParkingSlot(i, (i % 3 == 0 ? "A" : i % 3 == 1 ? "B" : "C"),
                                               (i / 15) + 1, distFromEntry);
            table.put(i, slot);
            heap.insert(i, distFromEntry);
        }
    }

    // ── PUBLIC API ─────────────────────────────────────────────────

    /**
     * Find nearest available slot using BFS (unweighted hop distance),
     * then allocate it and compute Dijkstra's route. O((V+E) log V)
     */
    public int findAndAllocateNearestSlot() {
        // Step 1: BFS to find nearest available slot by hop count
        int slotId = BFS.nearestAvailableSlot(graph, table, entryNode);
        if (slotId == -1) {
            // Fallback: try heap (by weighted distance)
            slotId = extractNextAvailableFromHeap();
        }
        if (slotId == -1) return -1;

        // Step 2: Allocate
        ParkingSlot slot = table.get(slotId);
        slot.allocate();
        availableCount--;
        return slotId;
    }

    /**
     * Compute and return the shortest driving path to a slot.
     * Uses Dijkstra's algorithm. O((V+E) log V)
     */
    public String getShortestRoute(int targetSlot) {
        Dijkstra dijk = new Dijkstra(graph, entryNode);
        return dijk.pathString(targetSlot);
    }

    /**
     * Return shortest path distance (metres) to a slot.
     */
    public double getShortestDistance(int targetSlot) {
        Dijkstra dijk = new Dijkstra(graph, entryNode);
        return dijk.distanceTo(targetSlot);
    }

    /**
     * Display all available slots sorted by distance using Merge Sort.
     * O(n log n)
     */
    public void displaySortedSlots() {
        // Collect available slots from hash table
        ParkingSlot[] available = new ParkingSlot[availableCount];
        int idx = 0;
        for (int i = 0; i < totalSlots; i++) {
            ParkingSlot s = table.get(i);
            if (s != null && s.isAvailable()) available[idx++] = s;
        }

        MergeSort.sort(available);

        System.out.println("\nAvailable Slots (Merge Sorted by distance):");
        System.out.printf("%-8s %-8s %-8s %-10s%n", "Slot ID", "Type", "Floor", "Distance(m)");
        System.out.println("------------------------------------------");
        for (ParkingSlot slot : available) {
            if (slot != null) {
                System.out.printf("%-8d %-8s %-8d %-10.1f%n",
                    slot.getId(), slot.getType(), slot.getFloor(), slot.getDistance());
            }
        }
    }

    /** Free a slot by ID — updates hash table. O(1) avg. */
    public void freeSlot(int slotId) {
        ParkingSlot slot = table.get(slotId);
        if (slot != null && !slot.isAvailable()) {
            slot.free();
            availableCount++;
            // Re-insert into heap (lazy deletion handles old stale entry)
            heap.insert(slotId, slot.getDistance());
            System.out.println("Slot " + slotId + " is now free.");
        } else {
            System.out.println("Slot " + slotId + " is already available or does not exist.");
        }
    }

    /** Manually occupy a slot (pre-simulation). */
    public void occupySlot(int slotId) {
        ParkingSlot slot = table.get(slotId);
        if (slot != null && slot.isAvailable()) {
            slot.occupy();
            availableCount--;
        }
    }

    /** Lookup a slot record by ID in O(1) avg. */
    public ParkingSlot lookupSlot(int slotId) {
        return table.get(slotId);
    }

    public void showStats() {
        System.out.printf("Total: %d | Available: %d | Occupied: %d | Graph nodes: %d | Edges: %d%n",
            totalSlots, availableCount, totalSlots - availableCount,
            graph.getNumNodes(), graph.getEdgeCount());
    }

    // ── private helpers ────────────────────────────────────────────

    /** Drain heap with lazy deletion until an available slot is found. */
    private int extractNextAvailableFromHeap() {
        while (!heap.isEmpty()) {
            int id   = heap.extractMin();
            ParkingSlot s = table.get(id);
            if (s != null && s.isAvailable()) return id;
        }
        return -1;
    }

    /** Benchmark-only: runs merge sort silently (no stdout). */
    public void mergeSortBenchmark() {
        ParkingSlot[] available = new ParkingSlot[availableCount];
        int idx = 0;
        for (int i = 0; i < totalSlots; i++) {
            ParkingSlot s = table.get(i);
            if (s != null && s.isAvailable()) available[idx++] = s;
        }
        MergeSort.sort(available);
    }

    /** Benchmark-only: runs displaySortedSlots without printing. */
    public void displaySortedSlotsQuiet() { mergeSortBenchmark(); }
}
