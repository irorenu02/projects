package lab6;

public class LinkedListQueue implements Queue {

	 private Node front;
	 private Node rear;
	 private int count;

	 public LinkedListQueue() {
	     front = null;
	     rear = null;
	     count = 0;
	 }

	 @Override
	 public boolean enqueue(String data) {
	     if (data == null) return false;

	     Node newNode = new Node(data);

	     if (isEmpty()) {
	         front = rear = newNode;
	     } else {
	         rear.next = newNode;
	         rear = newNode;
	     }

	     count++;
	     return true;
	 }

	 @Override
	 public String dequeue() {
	     if (isEmpty()) return null;

	     String value = front.data;
	     front = front.next;

	     if (front == null) {
	         rear = null;
	     }

	     count--;
	     return value;
	 }

	 @Override
	 public String peek() {
	     if (isEmpty()) return null;
	     return front.data;
	 }

	 @Override
	 public boolean isEmpty() {
	     return front == null;
	 }

	 @Override
	 public int getCount() {
	     return count;
	 }

	 @Override
	 public void display() {
	     if (isEmpty()) {
	         System.out.println("[empty]");
	         return;
	     }

	     Node current = front;
	     while (current != null) {
	         System.out.print(current.data);

	         if (current.next != null) {
	             System.out.print(" ");
	         }

	         current = current.next;
	     }
	     System.out.println();
	 }
	}
