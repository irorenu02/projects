package DevProject;

import java.util.Arrays;

public class ParkingBenchmark {

    // number of timed repetitions per test cell
    private static final int REPS = 5;

    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000};

        System.out.println("=============================================================");
        System.out.println("  PARKING SYSTEM — REPRODUCIBLE BENCHMARK");
        System.out.println("  Repetitions per cell: " + REPS);
        System.out.println("=============================================================\n");

        for (int n : sizes) {
            System.out.println("--- n = " + n + " slots ---");
            runAllBenchmarks(n);
            System.out.println();
        }
    }

    // top-level runner for one slot count
    private static void runAllBenchmarks(int n) {
        benchmarkAllocation(n);
        benchmarkFreeAndLookup(n);
        benchmarkSorting(n);
        benchmarkRouting(n);
    }

    // slot Allocation  — MinHeap extractMin  O(log n)
    private static void benchmarkAllocation(int n) {
        long[] times = new long[REPS];

        for (int r = 0; r < REPS; r++) {
            OptimizedParkingSystem sys = buildSystem(n);

            long start = System.nanoTime();
            // allocate all available slots
            while (sys.findOptimalSlot(0) != -1) { /* drain */ }
            times[r] = System.nanoTime() - start;
        }

        printResult("Slot Allocation   (MinHeap extractMin, O(log n))", n, times);
    }

    // slot Lookup/free  — HashTable get  O(1) average
   
    private static void benchmarkFreeAndLookup(int n) {
        long[] times = new long[REPS];

        for (int r = 0; r < REPS; r++) {
            OptimizedParkingSystem sys = buildSystem(n);

            // Pre-allocate everything so we have slots to free
            int allocated = 0;
            while (sys.findOptimalSlot(0) != -1) { allocated++; }

            long start = System.nanoTime();
            // Free and re-look-up every slot
            for (int id = 1; id <= n; id++) {
                sys.freeSlot(id);          // HashTable get + heap insert
            }
            times[r] = System.nanoTime() - start;
        }

        printResult("Slot Lookup/Free  (HashTable get,    O(1) avg)", n, times);
    }

    // sorting display - MergeSort  O(n log n)
    
    private static void benchmarkSorting(int n) {
        long[] times = new long[REPS];

        for (int r = 0; r < REPS; r++) {
            OptimizedParkingSystem sys = buildSystem(n);
            javax.swing.JTextArea dummy = new javax.swing.JTextArea();

            long start = System.nanoTime();
            sys.displaySortedSlotsGUI(dummy);
            times[r] = System.nanoTime() - start;
        }

        printResult("Sorting display   (MergeSort,        O(n log n))", n, times);
    }

    // route computation  — Dijkstra  O((V+E) log V)
    private static void benchmarkRouting(int n) {
        long[] times = new long[REPS];

        for (int r = 0; r < REPS; r++) {
            OptimizedParkingSystem sys = buildSystem(n);

            long start = System.nanoTime();
            // compute path from entry (0) to every slot
            for (int id = 1; id <= n; id++) {
                sys.getShortestPath(0, id);
            }
            times[r] = System.nanoTime() - start;
        }

        printResult("Route computation (Dijkstra,  O((V+E) log V))", n, times);
    }

    // helpers

    /**
     * Build a fresh OptimizedParkingSystem with n slots.
     * The system's RECORDS_FILE constant is "parking_records_optimized.txt";
     * if that file is absent the system initializes slots from scratch,
     * which is the correct behaviour for isolated benchmarking.
     */
    private static OptimizedParkingSystem buildSystem(int n) {
        return new OptimizedParkingSystem(n);
    }

    private static void printResult(String label, int n, long[] timesNs) {
        long sum = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (long t : timesNs) {
            sum += t;
            if (t < min) min = t;
            if (t > max) max = t;
        }
        double avgMs  = (sum / (double) timesNs.length) / 1_000_000.0;
        double minMs  = min  / 1_000_000.0;
        double maxMs  = max  / 1_000_000.0;

        System.out.printf(
            "  %-55s  avg=%7.3f ms  min=%7.3f ms  max=%7.3f ms%n",
            label, avgMs, minMs, maxMs
        );
    }
}
