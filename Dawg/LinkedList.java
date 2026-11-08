package Dawg;

public class LinkedList implements LinkedListInterface{
	
	    private Node head;
	    private int count;

	    public LinkedList() {
	        head = null;
	        count = 0;
	    }

	    // check if list is empty
	    // return true if head is null, otherwise false
	    public boolean isEmpty() {
	        return head == null;
	    }

	    // create a new node with given data
	    // point new node's next to current head
	    // update head to new node
	    // increment count
	    // return true
	    public boolean insert(String data) {
	        Node newNode = new Node(data);
	        newNode.next = head;
	        head = newNode;
	        count++;
	        return true;
	    }

	    //check if list is empty > return false
	    //traverse list using current and previous nodes
	    //if match found:
	    //if it's head > move head to next node
	    //else link previous to current.next
	    //decrement count
	    //return true
	    //if not found > return false
	    public boolean delete(String data) {
	        if (isEmpty()) return false;

	        Node current = head;
	        Node previous = null;

	        while (current != null) {
	            if (current.data.equals(data)) {
	                if (previous == null) {
	                    head = current.next;
	                } else {
	                    previous.next = current.next;
	                }
	                count--;
	                return true;
	            }
	            previous = current;
	            current = current.next;
	        }
	        return false;
	    }

	    //initialize index = 0
	    //traverse list
	    //if match found > return index
	    //increment index each step
	    //if not found > return -1
	    public int search(String data) {
	        Node current = head;
	        int index = 0;

	        while (current != null) {
	            if (current.data.equals(data)) {
	                return index;
	            }
	            current = current.next;
	            index++;
	        }
	        return -1;
	    }

	    // Traverse List
	    // if old data found
	    //  --replace new date
	    // 	--return true
	    // if not found, return false
	    public boolean update(String oldData, String newData) {
	        Node current = head;

	        while (current != null) {
	            if (current.data.equals(oldData)) {
	                current.data = newData;
	                return true;
	            }
	            current = current.next;
	        }
	        return false;
	    }

	    // check if list is empty
	    // if empty iprint
	    // else traverse then print each node
	    public void display() {
	        if (isEmpty()) {
	            System.out.println("Member list is empty.");
	            return;
	        }

	        Node current = head;
	        int index = 0;

	        while (current != null) {
	            System.out.println(index + ": " + current.data);
	            current = current.next;
	            index++;
	        }
	    }

	    // return count variable
	    public int getCount() {
	        return count;
	    }

	}

