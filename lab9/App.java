package lab9;

public class App {
    public static void main(String[] args) {
        BinaryHeap heap = new ArrayBinaryHeap(7);

        System.out.println("SCHOOL CLUB EVENT PRIORITY SYSTEM");
        System.out.println("Laboratory Activity 9: Binary Heap Implementation Using Array");
        System.out.println("Heap is empty: " + heap.isEmpty());
        System.out.println("Heap is full: " + heap.isFull());
        System.out.println("Current heap size: " + heap.getCount());
        System.out.println("Highest priority on empty heap: " + heap.peek());
        System.out.println();

        System.out.println("Insert priority 40: " + heap.insert(40));
        System.out.println("Insert priority 70: " + heap.insert(70));
        System.out.println("Insert priority 30: " + heap.insert(30));
        System.out.println("Insert priority 90: " + heap.insert(90));
        System.out.println("Insert priority 60: " + heap.insert(60));
        heap.display();
        System.out.println("Highest priority: " + heap.peek());
        System.out.println("Search priority 60: " + heap.search(60));
        System.out.println("Search priority 25: " + heap.search(25));
        System.out.println("Heap is full: " + heap.isFull());
        System.out.println("Current heap size: " + heap.getCount());
        System.out.println();

        System.out.println("Insert priority 85: " + heap.insert(85));
        System.out.println("Insert priority 50: " + heap.insert(50));
        System.out.println("Insert priority 100 when heap is full: " + heap.insert(100));
        heap.display();
        System.out.println("Heap is full: " + heap.isFull());
        System.out.println("Highest priority: " + heap.peek());
        System.out.println("Current heap size: " + heap.getCount());
        System.out.println();

        System.out.println("Remove highest priority: " + heap.remove());
        heap.display();
        System.out.println("Remove highest priority: " + heap.remove());
        heap.display();
        System.out.println("Highest priority: " + heap.peek());
        System.out.println("Heap is empty: " + heap.isEmpty());
        System.out.println("Heap is full: " + heap.isFull());
        System.out.println("Current heap size: " + heap.getCount());
        System.out.println();

        System.out.println("Remove highest priority: " + heap.remove());
        System.out.println("Remove highest priority: " + heap.remove());
        System.out.println("Remove highest priority: " + heap.remove());
        System.out.println("Remove highest priority: " + heap.remove());
        System.out.println("Remove highest priority: " + heap.remove());
        heap.display();
        System.out.println("Remove from empty heap: " + heap.remove());
        System.out.println("Highest priority on empty heap: " + heap.peek());
        System.out.println("Search priority 40 on empty heap: " + heap.search(40));
        System.out.println("Heap is empty: " + heap.isEmpty());
        System.out.println("Heap is full: " + heap.isFull());
        System.out.println("Current heap size: " + heap.getCount());
    }
}
