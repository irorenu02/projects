package lab6;

//Queue interface defining required queue operations
public interface Queue {
	boolean enqueue(String data);

	String dequeue();

	String peek();

	boolean isEmpty();

	int getCount();

	void display();
}