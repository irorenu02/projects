package DogPackage;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SalesSystem  extends JFrame {
	
	private JTextField firstNameField, middleNameField, lastNameField, birthYearField,quantityField;
	private JComboBox<String>genderBox;
	private JCheckBox withDrinks;
	
	public SalesSystem() {
		super("Nabunturan Burger Sales System");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,550);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		JLabel title = new JLabel("Nabunturan shit burger salesystem");
		title.setFont(new Font("Arial", Font.BOLD,16));
		title.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		
		
		

}
}
