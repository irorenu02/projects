package lab9;

public class ArrayBinaryHeap implements BinaryHeap {
    private final int[] heap;
    private int count;

    public ArrayBinaryHeap() {
        this(10);
    }

    public ArrayBinaryHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }

        heap = new int[capacity];
        count = 0;
    }
    
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i]  = heap[j];
        heap[j]  = temp;
    }

    private void heapifyUp(int startIndex) {
        int current = startIndex;
        int parent = (current - 1) / 2;
        
        while (current > 0 && heap[current] > heap[parent]) {
            swap(current, parent);
            current = parent;
            parent = (current - 1) / 2;
        }
        
    }
    
    private void heapifyDown(int startIndex) {
        int current = startIndex;
        int left = 2 * current + 1;
        
        while (left < count) {
        	
            int largestChild = left;
            int right = 2 * current + 2;
            if (right < count && heap[right] > heap[largestChild]) {
            	
                largestChild = right;
            }

            if (heap[current] >= heap[largestChild]) {
                break;
            }

            swap(current, largestChild);
            current = largestChild;
            left = 2 * current + 1;
        }
     
    }
    /*
     * Algorithm:
     * 1. Check if the heap is full.
     * 2. If it is full, return false.
     * 3. Insert the new data at the next available array position.
     * 4. Move the new data upward while it is greater than its parent.
     * 5. Increase the heap count by 1.
     * 6. Return true.
     */
    @Override
    public boolean insert(int data) {
        if (isFull()) {
            return false;
        }

        heap[count] = data;
        heapifyUp(count);
        count++;
        return true;
    }

    public Integer remove() {
        if (isEmpty()) {
            return null;
        }

        int removed = heap[0];
        heap[0] = heap[count - 1];
        count--;

        if (count > 0) {
            heapifyDown(0);
        }

        return removed;
    }

    public Integer peek() {
        if (isEmpty()) {
            return null;
        }

        return heap[0];
    }

    public boolean search(int data) {
        if (isEmpty()) {
            return false;
        }

        for (int i = 0; i < count; i++) {
            if (heap[i] == data) {
            	
                return true;
            }
        }
        
        return false;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == heap.length;
    }

    public int getCount() {
        return count;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Heap elements: [empty]");
            return;
        }
        
        System.out.print("Heap elements (level order): ");
        for (int i = 0; i < count; i++) {
            System.out.print(heap[i]);
            if (i < count - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }
}