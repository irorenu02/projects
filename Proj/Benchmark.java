package Proj;

/**
 * Empirical benchmark comparing Baseline vs Optimized systems.
 * Tests three operations independently:
 *   1. Slot sorting (Bubble Sort vs Merge Sort) — shows O(n²) vs O(n log n)
 *   2. Slot search/allocation (Linear scan vs BFS+Heap)
 *   3. Combined workload
 *
 * Metrics: System.nanoTime() converted to ms.
 * Repetitions: 6 runs per test; first discarded (JVM warm-up).
 * Same dataset for both systems.
 */
public class Benchmark {

    private static final int   REPS       = 6;
    private static final int[] TEST_SIZES = {50, 200, 500};

    public static void run() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              EMPIRICAL PERFORMANCE BENCHMARK                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");

        runSortingBenchmark();
        runSearchBenchmark();
        runCombinedBenchmark();
    }

    // ── 1. SORTING BENCHMARK: Bubble Sort vs Merge Sort ───────────────────────

    private static void runSortingBenchmark() {
        System.out.println("\n[ SORTING: Bubble Sort (Baseline) vs Merge Sort (Optimized) ]");
        System.out.printf("%-16s %-22s %-22s %-14s%n",
            "Input Size", "Bubble Sort (ms)", "Merge Sort (ms)", "Improvement(%)");
        System.out.println("──────────────────────────────────────────────────────────────────────");

        for (int n : TEST_SIZES) {
            // Build a shuffled slot array
            ParkingSlot[] slots = buildSlots(n);

            double bubbleTime = measureBubbleSort(slots, n);
            double mergeTime  = measureMergeSort(slots, n);
            double pct = ((bubbleTime - mergeTime) / bubbleTime) * 100.0;

            System.out.printf("%-16s %-22.4f %-22.4f %.1f%%%n",
                n + " slots", bubbleTime, mergeTime, pct);
        }
    }

    private static double measureBubbleSort(ParkingSlot[] base, int n) {
        double total = 0;
        for (int rep = 0; rep < REPS; rep++) {
            ParkingSlot[] arr = copyArray(base, n);
            long t0 = System.nanoTime();
            bubbleSort(arr);
            long t1 = System.nanoTime();
            if (rep > 0) total += (t1 - t0) / 1_000_000.0;
        }
        return total / (REPS - 1);
    }

    private static double measureMergeSort(ParkingSlot[] base, int n) {
        double total = 0;
        for (int rep = 0; rep < REPS; rep++) {
            ParkingSlot[] arr = copyArray(base, n);
            long t0 = System.nanoTime();
            MergeSort.sort(arr);
            long t1 = System.nanoTime();
            if (rep > 0) total += (t1 - t0) / 1_000_000.0;
        }
        return total / (REPS - 1);
    }

    // ── 2. SEARCH BENCHMARK: Linear Scan vs BFS+Heap ─────────────────────────

    private static void runSearchBenchmark() {
        System.out.println("\n[ SEARCH/ALLOCATION: Linear Scan (Baseline) vs BFS+Heap (Optimized) ]");
        System.out.printf("%-16s %-22s %-22s %-14s%n",
            "Input Size", "Linear Scan (ms)", "BFS+Heap (ms)", "Improvement(%)");
        System.out.println("──────────────────────────────────────────────────────────────────────");

        for (int n : TEST_SIZES) {
            double baseTime = measureBaselineSearch(n);
            double optTime  = measureOptimizedSearch(n);
            double pct = ((baseTime - optTime) / baseTime) * 100.0;

            System.out.printf("%-16s %-22.4f %-22.4f %.1f%%%n",
                n + " slots", baseTime, optTime, pct);
        }
    }

    private static double measureBaselineSearch(int n) {
        double total = 0;
        for (int rep = 0; rep < REPS; rep++) {
            BaselineParkingSystem sys = new BaselineParkingSystem(n);
            // Occupy every other slot so search has to scan further
            for (int i = 0; i < n - 1; i += 2) sys.occupySlot(i);

            long t0 = System.nanoTime();
            // Perform n linear searches (reset after each to keep slots available)
            for (int q = 0; q < n; q++) {
                sys.findAndAllocateNearestSlot(0);
            }
            long t1 = System.nanoTime();
            if (rep > 0) total += (t1 - t0) / 1_000_000.0;
        }
        return total / (REPS - 1);
    }

    private static double measureOptimizedSearch(int n) {
        double total = 0;
        for (int rep = 0; rep < REPS; rep++) {
            OptimizedParkingSystem sys = new OptimizedParkingSystem(n);
            for (int i = 0; i < n - 1; i += 2) sys.occupySlot(i);

            long t0 = System.nanoTime();
            for (int q = 0; q < n; q++) {
                sys.findAndAllocateNearestSlot();
            }
            long t1 = System.nanoTime();
            if (rep > 0) total += (t1 - t0) / 1_000_000.0;
        }
        return total / (REPS - 1);
    }

    // ── 3. COMBINED BENCHMARK: search + sort per cycle ────────────────────────

    private static void runCombinedBenchmark() {
        System.out.println("\n[ COMBINED: Allocation + Sort Display per vehicle cycle ]");
        System.out.printf("%-16s %-22s %-22s %-14s%n",
            "Input Size", "Baseline (ms)", "Optimized (ms)", "Improvement(%)");
        System.out.println("──────────────────────────────────────────────────────────────────────");

        for (int n : TEST_SIZES) {
            double baseTime = measureBaselineCombined(n);
            double optTime  = measureOptimizedCombined(n);
            double pct = ((baseTime - optTime) / baseTime) * 100.0;

            System.out.printf("%-16s %-22.4f %-22.4f %.1f%%%n",
                n + " slots", baseTime, optTime, pct);
        }
        System.out.println("──────────────────────────────────────────────────────────────────────");
        System.out.println("Repetitions: 5 (first run discarded). Same dataset for both systems.");
    }

    private static double measureBaselineCombined(int n) {
        double total = 0;
        for (int rep = 0; rep < REPS; rep++) {
            BaselineParkingSystem sys = new BaselineParkingSystem(n);
            for (int i = 0; i < n; i += 3) sys.occupySlot(i);

            long t0 = System.nanoTime();
            for (int q = 0; q < n / 2; q++) {
                sys.findAndAllocateNearestSlot(0); // O(n)
                sys.bubbleSortBenchmark();          // O(n²)
            }
            long t1 = System.nanoTime();
            if (rep > 0) total += (t1 - t0) / 1_000_000.0;
        }
        return total / (REPS - 1);
    }

    private static double measureOptimizedCombined(int n) {
        double total = 0;
        for (int rep = 0; rep < REPS; rep++) {
            OptimizedParkingSystem sys = new OptimizedParkingSystem(n);
            for (int i = 0; i < n; i += 3) sys.occupySlot(i);

            long t0 = System.nanoTime();
            for (int q = 0; q < n / 2; q++) {
                sys.findAndAllocateNearestSlot(); // BFS O(V+E)
                sys.mergeSortBenchmark();          // O(n log n)
            }
            long t1 = System.nanoTime();
            if (rep > 0) total += (t1 - t0) / 1_000_000.0;
        }
        return total / (REPS - 1);
    }

    // ── private helpers ────────────────────────────────────────────────────────

    private static ParkingSlot[] buildSlots(int n) {
        ParkingSlot[] arr = new ParkingSlot[n];
        // Reverse order so sort has maximum work to do
        for (int i = 0; i < n; i++) {
            double dist = (n - i) * 0.5 + 10.0;
            arr[i] = new ParkingSlot(i, "A", (i / 15) + 1, dist);
        }
        return arr;
    }

    private static ParkingSlot[] copyArray(ParkingSlot[] src, int n) {
        ParkingSlot[] copy = new ParkingSlot[n];
        System.arraycopy(src, 0, copy, 0, n);
        return copy;
    }

    private static void bubbleSort(ParkingSlot[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j].getDistance() > arr[j+1].getDistance()) {
                    ParkingSlot t = arr[j]; arr[j] = arr[j+1]; arr[j+1] = t;
                }
    }
}
