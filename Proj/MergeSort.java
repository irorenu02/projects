package Proj;

/**
 * Custom Merge Sort for ParkingSlot arrays.
 * Stable sort — equal distances preserve secondary order (floor, then id).
 * Implemented from scratch; no Arrays.sort() used.
 * Time complexity: O(n log n) best/average/worst.
 * Space complexity: O(n) auxiliary.
 */
public class MergeSort {

    /** Sort slots by distance (primary), floor (secondary), id (tertiary). */
    public static void sort(ParkingSlot[] arr) {
        if (arr == null || arr.length <= 1) return;
        ParkingSlot[] aux = new ParkingSlot[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }

    // ── private helpers ────────────────────────────────────────────

    private static void mergeSort(ParkingSlot[] arr, ParkingSlot[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, aux, lo,    mid);
        mergeSort(arr, aux, mid+1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private static void merge(ParkingSlot[] arr, ParkingSlot[] aux, int lo, int mid, int hi) {
        // Copy to auxiliary array
        for (int k = lo; k <= hi; k++) aux[k] = arr[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              arr[k] = aux[j++];
            else if (j > hi)               arr[k] = aux[i++];
            else if (compare(aux[j], aux[i]) < 0) arr[k] = aux[j++]; // stable: j only wins on strict less
            else                           arr[k] = aux[i++];
        }
    }

    /** Comparator: distance → floor → id */
    private static int compare(ParkingSlot a, ParkingSlot b) {
        if (a.getDistance() != b.getDistance())
            return Double.compare(a.getDistance(), b.getDistance());
        if (a.getFloor() != b.getFloor())
            return Integer.compare(a.getFloor(), b.getFloor());
        return Integer.compare(a.getId(), b.getId());
    }
}
