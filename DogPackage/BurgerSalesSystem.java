package DogPackage;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class BurgerSalesSystem extends JFrame {

    private JTextField firstNameField, middleNameField, lastNameField, birthYearField, quantityField;
    private JComboBox<String> genderBox;
    private JCheckBox withDrinks;

    public BurgerSalesSystem() {
        super("Ticket Sales System");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("TICKET SALES SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(makeField("First Name:", firstNameField = new JTextField(15)));
        panel.add(makeField("Middle Name:", middleNameField = new JTextField(15)));
        panel.add(makeField("Last Name:", lastNameField = new JTextField(15)));
        panel.add(makeField("Birth Year:", birthYearField = new JTextField(15)));

        genderBox = new JComboBox<>(new String[]{"--Select--", "Male", "Female", "Prefer not to say"});
        panel.add(makeField("Gender:", genderBox));

        panel.add(makeField("Quantity:", quantityField = new JTextField(15)));

        withDrinks = new JCheckBox("With Drinks");
        withDrinks.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(withDrinks);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("PROCESS ORDER");
        JButton searchButton = new JButton("SEARCH CUSTOMER");

        addButton.setBackground(new Color(0x28A745));
        addButton.setForeground(Color.BLACK);
        searchButton.setBackground(new Color(0x007BFF));
        searchButton.setForeground(Color.BLACK);

        addButton.addActionListener(e -> processOrder());
        searchButton.addActionListener(e -> searchCustomer());

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }
    private JPanel makeField(String labelText, JComponent field) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 25));
        p.add(label, BorderLayout.WEST);
        p.add(field, BorderLayout.CENTER);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return p;
    }
    private void processOrder() {
        String firstName = firstNameField.getText().trim();
        String middleName = middleNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String birthYearText = birthYearField.getText().trim();
        String gender = genderBox.getSelectedItem().toString();
        String quantityText = quantityField.getText().trim();

        if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty()
                || birthYearText.isEmpty() || quantityText.isEmpty()
                || gender.equals("--Select--")) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int birthYear, quantity;
        try {
            birthYear = Integer.parseInt(birthYearText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Birth Year must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int pricePerBurger = withDrinks.isSelected() ? 60 : 40;
        int totalPrice = pricePerBurger * quantity;
        String orderType = withDrinks.isSelected() ? "with Drinks" : "F";

        String customerName = firstName + " " + middleName.charAt(0) + ". " + lastName;

        try (FileWriter writer = new FileWriter("/Users/makima/Desktop/untitled folder/School/src/DogPackage/customers.txt", true)) {
            writer.write(firstName + "," + middleName + "," + lastName + "," +
                    birthYear + "," + gender + "," + quantity + "," + totalPrice + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file.", "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Transaction Complete\n\n" +
                        "Customer Name: " + customerName +
                        "\nOrder: " + orderType +
                        "\nTotal Price: " + totalPrice,
                "Confirmation", JOptionPane.INFORMATION_MESSAGE);

        firstNameField.setText("");
        middleNameField.setText("");
        lastNameField.setText("");
        birthYearField.setText("");
        quantityField.setText("");
        genderBox.setSelectedIndex(0);
        withDrinks.setSelected(false);
    }

    // search method
    private void searchCustomer() {
        String searchName = JOptionPane.showInputDialog(this, "Enter First Name to Search:");
        if (searchName == null || searchName.trim().isEmpty()) {
            return;
        }

        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/makima/Desktop/untitled folder/School/src/DogPackage/customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(searchName.trim())) {
                    found = true;
                    JOptionPane.showMessageDialog(this,
                            "Customer Found!\n\n" +
                                    "Name: " + parts[0] + " " + parts[1].charAt(0) + ". " + parts[2] +
                                    "\nBirth Year: " + parts[3] +
                                    "\nGender: " + parts[4] +
                                    "\nQuantity: " + parts[5] +
                                    "\nTotal Price: " + parts[6],
                            "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file.", "File Error", JOptionPane.ERROR_MESSAGE);
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No customer found with that First Name.", "Not Found", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BurgerSalesSystem::new);
    }
}
