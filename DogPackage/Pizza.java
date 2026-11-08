package DogPackage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pizza {
	public static void main(String[] args) throws IOException {

		// File Creation
		File ordersheet = new File("/Users/makima/Desktop/untitled folder/School/src/DogPackage/combosheet.txt");
		ordersheet.createNewFile();

		// Array of Options for ComboBox
		String[] crustType = { "Select a crust", "THICC", "thin" };
		String[] flavours = { "Select flavor", "All Cheese", "Pepperoni", "Hawaiian (ham, bacon, pineapple)",
				"Supreme(pepperoni, bacon, mushroom, olives)", "Ham Delight (all ham)" };
		String[] sizes = { "Select a size", "9", "11", "14", "16", "18" };

		// All Components
		JLabel cnameLabel = new JLabel(), crustLabel = new JLabel(), flavorLabel = new JLabel(),
				sizeLabel = new JLabel(), quantityLabel = new JLabel(), addressLabel = new JLabel(),
				priceLabel = new JLabel();
		JTextField cnameTF = new JTextField(), quantityTF = new JTextField(), addressTF = new JTextField();
		JComboBox<String> crustBox = new JComboBox<String>(crustType), flavorBox = new JComboBox<String>(flavours),
				sizeBox = new JComboBox<String>(sizes);
		JButton submitButton = new JButton("SUBMIT");

		// Main Frame
		JFrame frame = new JFrame();
		frame.setTitle("Albertos Pizza Order Form");
		frame.setSize(450, 450);
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		// Layout for Components
		GridLayout grid1 = new GridLayout(7, 2);
		grid1.setHgap(-140);
		GridLayout grid2 = new GridLayout(1, 1);

		// Panel to store Main components
		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(10, 10, 410, 270);
		mainpanel.setLayout(grid1);
		frame.add(mainpanel);

		// Main Components
		cnameLabel.setText("Customer Name:");
		mainpanel.add(cnameLabel);
		mainpanel.add(cnameTF);
		crustLabel.setText("Pizza Crust:");
		mainpanel.add(crustLabel);
		mainpanel.add(crustBox);
		flavorLabel.setText("Flavor:");
		mainpanel.add(flavorLabel);
		mainpanel.add(flavorBox);
		sizeLabel.setText("Size:");
		mainpanel.add(sizeLabel);
		mainpanel.add(sizeBox);
		quantityLabel.setText("Quantity:");
		mainpanel.add(quantityLabel);
		mainpanel.add(quantityTF);
		addressLabel.setText("Address:");
		mainpanel.add(addressLabel);
		mainpanel.add(addressTF);
		priceLabel.setText("Price: Php. 100/pizza");
		mainpanel.add(priceLabel);

		// Button Panel and Component
		JPanel buttonpanel = new JPanel();
		buttonpanel.setBounds(10, 300, 410, 80);
		buttonpanel.setLayout(grid2);
		buttonpanel.add(submitButton);
		frame.add(buttonpanel);
		submitButton.addActionListener(new ActionListener() {
			@Override // Button Action
			public void actionPerformed(ActionEvent e) {
				if (!cnameTF.getText().isEmpty() && crustBox.getSelectedIndex() != 0
						&& flavorBox.getSelectedIndex() != 0 && sizeBox.getSelectedIndex() != 0
						&& !quantityTF.getText().isEmpty() && !addressTF.getText().isEmpty()) {
					try {
						// To Calculate Pizza price by quantity and show amount
						int quantity = Integer.parseInt(quantityTF.getText().trim());
						int totalprice = quantity * 100;
						JOptionPane.showMessageDialog(frame, "Your order total amount: " + totalprice, "TOTAL AMOUNT",
								JOptionPane.INFORMATION_MESSAGE);

						// What to Write in FILE
						String toWrite = cnameTF.getText() + "," + crustBox.getSelectedItem() + ","
								+ flavorBox.getSelectedItem() + "," + sizeBox.getSelectedItem() + ","
								+ quantityTF.getText() + "," + addressTF.getText() + "," + totalprice + "\n";
						Writer(ordersheet, toWrite);

						JOptionPane.showMessageDialog(frame, "Order Received! Thank you and order again!");
						frame.dispose();

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(frame, "Quantity must be a number!", "INVALID INPUT",
								JOptionPane.ERROR_MESSAGE);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid input or lacking input! Try again.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	public static void Writer(File file, String toAppend) throws IOException {
		FileWriter fw = new FileWriter(file, true);
		fw.write(toAppend);
		fw.close();
	}
}
 