package DogPackage;

public interface ArrayInterface {
	boolean insert(String data);
	boolean delete(String data);
	int search(String data);
	boolean update(String oldData, String newData);
	void display();
	boolean isFull();
	boolean isEmpty();
	int getCount();
	}

