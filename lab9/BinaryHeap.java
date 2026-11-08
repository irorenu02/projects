package lab9;

public interface BinaryHeap {
	
    boolean insert(int data);
    Integer remove();
    Integer peek();
    boolean search(int data);
    boolean isEmpty();
    boolean isFull();
    int getCount();
    void display();
}