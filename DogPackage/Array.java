package DogPackage;

public class Array implements ArrayInterface {

	    private String[] elements;
	    private int size = 0;

	    public Array() {
	        this(10);
	    }

	    public Array(int capacity) {
	        if (capacity <= 0) {
	            throw new IllegalArgumentException("Capacity must be greater than 0.");
	        }
	        elements = new String[capacity];
	    }

	    public boolean insert(String name) {
	        if (isFull()) {
	            return false;
	        }
	        elements[size] = name;
	        size++;
	        return true;
	    }

	    public boolean isFull() {
	        return size >= elements.length;
	    }

	    public boolean isEmpty() {
	        return size == 0;
	    }

	    public int search(String name) {
	        for (int i = 0; i < size; i++) {
	            if (elements[i].equals(name)) {
	                return i;
	            }
	        }
	        return -1;
	    }

	    public boolean update(String oldName, String newName) {
	        int index = search(oldName);
	        if (index == -1) return false;

	        elements[index] = newName;
	        return true;
	    }

	    public boolean delete(String name) {
	        int index = search(name);
	        if (index == -1) return false;

	        for (int i = index; i < size - 1; i++) {
	            elements[i] = elements[i + 1];
	        }
	        size--;
	        return true;
	    }

	    public void display() {
	        if (isEmpty()) return;

	        System.out.print("Registered members: ");
	        for (int i = 0; i < size; i++) {
	            System.out.print(elements[i]);
	            if (i < size - 1) {
	                System.out.print(", ");
	            }
	        }
	        System.out.println();
	    }

	    public int getCount() {
	        return size;
	    }
	}

