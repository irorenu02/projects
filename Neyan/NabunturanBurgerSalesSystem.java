package Neyan;

import javax.swing.*;
import java.awt.*;

public class NabunturanBurgerSalesSystem extends JFrame {

	private JTextField firstNameField, middleNameField, lastNameField, quantityField;
	private JComboBox<Integer> birthYearBox;
    private JComboBox<String> genderBox;
    private JCheckBox withDrinksBox;

    private OrderManager orderManager = new OrderManager("/Users/makima/Desktop/untitled folder/School/src/Neyan/customers.txt");

    public NabunturanBurgerSalesSystem() {
        super("Nabunturan Burger Sales System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 520);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        JLabel title = new JLabel("NABUNTURAN BURGER SALES SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 12)));

        firstNameField = new JTextField(15);
        middleNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        genderBox = new JComboBox<>(new String[] { "--Select--", "Male", "Female", "Prefer not to say" });
        quantityField = new JTextField(15);
        withDrinksBox = new JCheckBox("With Drinks");

        panel.add(makeField("First Name:", firstNameField));
        panel.add(makeField("Middle Name:", middleNameField));
        panel.add(makeField("Last Name:", lastNameField));
        panel.add(makeField("Gender:", genderBox));
        panel.add(makeField("Quantity:", quantityField));
        
        birthYearBox = new JComboBox<>();
        for (int year = 2025; year >= 1950; year--) { 
            birthYearBox.addItem(year);
        }
        panel.add(makeField("Birth Year:", birthYearBox));

        withDrinksBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(withDrinksBox);

        panel.add(Box.createRigidArea(new Dimension(0, 12)));

        JButton processButton = new JButton("PROCESS ORDER");
        processButton.setBackground(new Color(0x28A745));
        processButton.setForeground(Color.WHITE);
        processButton.setFocusPainted(false);
        processButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        processButton.addActionListener(e -> processOrder());

        panel.add(processButton);

        add(panel);
        setVisible(true);
    }

    private JPanel makeField(String labelText, JComponent field) {
        JPanel p = new JPanel(new BorderLayout(6, 6));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(120, 26));
        p.add(label, BorderLayout.WEST);
        p.add(field, BorderLayout.CENTER);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        return p;
    }

    private void processOrder() {
        String first = firstNameField.getText().trim();
        String middle = middleNameField.getText().trim();
        String last = lastNameField.getText().trim();
        String gender = (String) genderBox.getSelectedItem();
        String qtyTxt = quantityField.getText().trim();
        boolean withDrinks = withDrinksBox.isSelected();

        if (first.isEmpty() || middle.isEmpty() || last.isEmpty()
                || qtyTxt.isEmpty()
                || gender == null || gender.equals("--Select--")) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer birthYear = (Integer) birthYearBox.getSelectedItem();
        if (birthYear == null) {
            JOptionPane.showMessageDialog(this, "Please select a Birth Year.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(qtyTxt);
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity must be a positive integer.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        Customer customer = new Customer(first, middle, last, birthYear, gender);
        Order order = new Order(quantity, withDrinks);

        
        try {
            orderManager.save(customer, order);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        String message = "Transaction Complete\n\n" +
                "Customer Name: " + customer.getFormattedName() +
                "\nOrder: " + order.getOrderType() +
                "\nTotal Price: " + order.getTotalPrice();

        JOptionPane.showMessageDialog(this, message, "Confirmation", JOptionPane.INFORMATION_MESSAGE);

       
        firstNameField.setText("");
        middleNameField.setText("");
        lastNameField.setText("");
        quantityField.setText("");
        genderBox.setSelectedIndex(0);
        withDrinksBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NabunturanBurgerSalesSystem::new);
    }
}
