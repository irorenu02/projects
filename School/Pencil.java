package School;

import javax.swing.JOptionPane;

public class Pencil {

private double length;

public static void main(String[] args) {
	
	//String brand = JOptionPane.showInputDialog("Enter Brand: ");
	//String hardness = JOptionPane.showInputDialog("Enter Hardness: ");
	//double length = Double.parseDouble(JOptionPane.showInputDialog("Enter Length: "));
	//boolean withEraser = Boolean.parseBoolean(JOptionPane.showInputDialog("Eraser?: "));
	//String color = JOptionPane.showInputDialog("Enter Color:");
	
Pencil lapis = new Pencil();

	String a = lapis.write("Nabunturan Burger");
		JOptionPane.showMessageDialog(null, a);
		double b = lapis.stab(60);
		JOptionPane.showMessageDialog(null,"Enemy attacked! The depth of wound is " + b + " kilometers.");
		
	lapis.draw("an image of Bart Simpson.");
	
		double d = lapis.sharpen(2);
		JOptionPane.showMessageDialog(null, "The sharpener removed some material from the pencil. The length is now: " + d);

	lapis.length = lapis.sharpen(2);

	System.out.println(lapis.measure());

	char[] e = lapis.measure();
		JOptionPane.showMessageDialog(null, "The length of the pencil is " + e);
}

private char[] measure() {
	
	return null;
}

private double sharpen(int i) {
	
	return 0;
}

private void draw(String string) {
	
	
}

private double stab(int i) {
	
	return 0;
}

private String write(String string) {
	
	return null;
}
}