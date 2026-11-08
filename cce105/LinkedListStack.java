package cce105;

public class LinkedListStack implements Stack {
	private Node top;
	private int count;

	public LinkedListStack() {
		top = null;
		count = 0;
	}

	public boolean push(String data) {
		if (data == null) {
			return false;
		}
		Node newNode = new Node(data);
		newNode.setNext(top);
		top = newNode;
		count++;
		return true;
	}

	public String pop() {
		if (isEmpty()) {
			return null;
		}
		String data = top.getData();
		top = top.getNext();
		count--;
		return data;
	}

	public String peek() {
		if (isEmpty()) {
			return null;
		}
		return top.getData();
	}

	public boolean isEmpty() {
		return top == null;
	}

	public int getCount() {
		return count;
	}

	public void display() {
		if (isEmpty()) {
			System.out.println("Stack elements: [empty]");
			return;
		}
		System.out.print("Stack elements (top to bottom): ");
		Node current = top;
		while (current != null) {
			System.out.print(current.getData());
			if (current.getNext() != null) {
				System.out.print(" ");
			}
			current = current.getNext();
		}
		System.out.println();
	}

}
