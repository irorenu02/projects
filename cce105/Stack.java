package cce105;

public interface Stack {
	boolean push(String data);
	String pop();
	String peek();
	boolean isEmpty();
	int getCount();
	void display();
}