package Dawg;

public interface LinkedListInterface {
	boolean insert(String data);
    boolean delete(String data);
    int search(String data);
    boolean update(String oldData, String newData);
    void display();
    boolean isEmpty();
    int getCount();

}
