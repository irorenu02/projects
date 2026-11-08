package lab6;

public class App {
	 public static void main(String[] args) {

	     System.out.println("SCHOOL CLUB MEMBER SERVICE REQUEST SYSTEM");
	     System.out.println("Laboratory Activity: Queue Implementation Using Linked List\n");

	     LinkedListQueue queue = new LinkedListQueue();

	     System.out.println("Queue is empty: " + queue.isEmpty());
	     System.out.println("Current queue size: " + queue.getCount() + "\n");

	     System.out.println("Enqueue Submit Registration Form: " + queue.enqueue("Submit Registration Form"));
	     System.out.println("Enqueue Verify Requirements: " + queue.enqueue("Verify Requirements"));
	     System.out.println("Enqueue Release ID Stub: " + queue.enqueue("Release ID Stub"));

	     System.out.print("Queue elements (front to rear): ");
	     queue.display();

	     System.out.println("Front item: " + queue.peek());
	     System.out.println("Current queue size: " + queue.getCount() + "\n");

	     System.out.println("Dequeue item: " + queue.dequeue());

	     System.out.print("Queue elements (front to rear): ");
	     queue.display();

	     System.out.println("Queue is empty: " + queue.isEmpty());
	     System.out.println("Current queue size: " + queue.getCount() + "\n");

	     System.out.println("Enqueue Issue Payment Slip: " + queue.enqueue("Issue Payment Slip"));
	     System.out.println("Enqueue Confirm Seminar Slot: " + queue.enqueue("Confirm Seminar Slot"));

	     System.out.print("Queue elements (front to rear): ");
	     queue.display();

	     System.out.println("Front item: " + queue.peek());
	     System.out.println("Current queue size: " + queue.getCount() + "\n");

	     System.out.println("Enqueue null request: " + queue.enqueue(null));

	     System.out.println("Dequeue item: " + queue.dequeue());
	     System.out.println("Dequeue item: " + queue.dequeue());
	     System.out.println("Dequeue item: " + queue.dequeue());
	     System.out.println("Dequeue item: " + queue.dequeue());

	     System.out.print("Queue elements: ");
	     queue.display();

	     System.out.println("Front item on empty queue: " + queue.peek());
	     System.out.println("Dequeue item on empty queue: " + queue.dequeue());

	     System.out.println("Queue is empty: " + queue.isEmpty());
	     System.out.println("Current queue size: " + queue.getCount());
	 }
	}