package Proj;

/**
 * Custom binary min-heap keyed on distance (double).
 * Stores (slotId, distance) pairs.
 * No Java PriorityQueue used.
 */
public class MinHeap {

    private static class HeapNode {
        int    slotId;
        double distance;

        HeapNode(int slotId, double distance) {
            this.slotId   = slotId;
            this.distance = distance;
        }
    }

    private HeapNode[] data;
    private int        size;

    public MinHeap(int capacity) {
        data = new HeapNode[capacity + 1]; // 1-indexed
        size = 0;
    }

    public boolean isEmpty() { return size == 0; }
    public int     getSize() { return size; }

    /** Insert a (slotId, distance) pair — O(log n). */
    public void insert(int slotId, double distance) {
        if (size >= data.length - 1) resize();
        size++;
        data[size] = new HeapNode(slotId, distance);
        siftUp(size);
    }

    /**
     * Remove and return the slot ID with minimum distance — O(log n).
     * Uses lazy deletion: callers should check isAvailable() on result.
     */
    public int extractMin() {
        if (isEmpty()) return -1;
        int minId = data[1].slotId;
        data[1] = data[size];
        data[size] = null;
        size--;
        if (!isEmpty()) siftDown(1);
        return minId;
    }

    /** Peek at minimum distance without removing — O(1). */
    public double peekMinDistance() {
        if (isEmpty()) return Double.MAX_VALUE;
        return data[1].distance;
    }

    // ── private helpers ────────────────────────────────────────────

    private void siftUp(int i) {
        while (i > 1) {
            int parent = i / 2;
            if (data[parent].distance > data[i].distance) {
                swap(parent, i);
                i = parent;
            } else break;
        }
    }

    private void siftDown(int i) {
        while (2 * i <= size) {
            int left  = 2 * i;
            int right = 2 * i + 1;
            int smallest = i;

            if (left  <= size && data[left].distance  < data[smallest].distance) smallest = left;
            if (right <= size && data[right].distance < data[smallest].distance) smallest = right;

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else break;
        }
    }

    private void swap(int a, int b) {
        HeapNode tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }

    private void resize() {
        HeapNode[] bigger = new HeapNode[data.length * 2];
        System.arraycopy(data, 0, bigger, 0, data.length);
        data = bigger;
    }
}
