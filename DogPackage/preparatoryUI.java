package DogPackage;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class preparatoryUI {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sample Title Here");
		frame.setSize(350, 500);
		frame.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setVisible(true);
		
		JLabel mainlabel = new JLabel("CREATE NEW ENTRY");
		mainlabel.setBounds(100, 0, 200, 50);
		frame.add(mainlabel);
		
		GridLayout grid = new GridLayout(8,2);
		
		String[] test = {"one","two","three"};
		
		JLabel label1 = new JLabel("First Name:"), label2 = new JLabel("Middle Name:"), label3 = new JLabel("Last Name:"),
				label4 = new JLabel("Age:"), label5 = new JLabel("Address:"), label6 = new JLabel("Password:"),
				label7 = new JLabel("Test:");
		JTextField textfield1 = new JTextField(), textfield2 = new JTextField(), textfield3 = new JTextField(), textfield4 = new JTextField(),
				textfield5 = new JTextField();
		JPasswordField passwordfield = new JPasswordField();
		JComboBox<String> combobox = new JComboBox<String>(test);
		JCheckBox checkbox = new JCheckBox("This is a Circle");
		JRadioButton radiobutton = new JRadioButton("This is a Square");
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 40, 315, 350);
		panel.setBackground(Color.GRAY);
		panel.setLayout(grid);
		frame.add(panel);
		
		panel.add(label1);
		panel.add(textfield1);
		panel.add(label2);
		panel.add(textfield2);
		panel.add(label3);
		panel.add(textfield3);
		panel.add(label4);
		panel.add(textfield4);
		panel.add(label5);
		panel.add(textfield5);
		panel.add(label6);
		panel.add(passwordfield);
		panel.add(label7);
		panel.add(combobox);
		checkbox.setBackground(Color.GRAY);
		panel.add(checkbox);
		radiobutton.setBackground(Color.GRAY);
		panel.add(radiobutton);
		
		JButton button = new JButton("ADD");
		button.setBounds(60,400, 200, 50);
		button.setBackground(Color.GREEN);
		frame.add(button);
	}
}
