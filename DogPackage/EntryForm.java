package DogPackage;

import java.awt.*;
import javax.swing.*;

public class EntryForm extends JFrame {

    private JTextField firstNameField, middleNameField, lastNameField, ageField, addressField;
    private JPasswordField passwordField;
    private JComboBox<String> genderBox;
    private JCheckBox agreeTerms;

    public EntryForm() {
        super("User Sign Up");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("CREATE NEW ACCOUNT");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(makeField("First Name:", firstNameField = new JTextField(15)));
        panel.add(makeField("Middle Name:", middleNameField = new JTextField(15)));
        panel.add(makeField("Last Name:", lastNameField = new JTextField(15)));
        panel.add(makeField("Age:", ageField = new JTextField(15)));
        panel.add(makeField("Address:", addressField = new JTextField(15)));

        passwordField = new JPasswordField(15);
        panel.add(makeField("Password:", passwordField));

        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        panel.add(makeField("Gender:", genderBox));

        agreeTerms = new JCheckBox("I agree to the Terms & Conditions");
        agreeTerms.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(agreeTerms);

        JButton signupButton = new JButton("SIGN UP");
        signupButton.setBackground(new Color(0xFF8C00));
        signupButton.setOpaque(true);
        signupButton.setForeground(Color.BLACK);
        signupButton.setFocusPainted(false);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(signupButton);

        signupButton.addActionListener(e -> handleSignup());

        add(panel);
        setVisible(true);
    }

    private JPanel makeField(String labelText, JComponent field) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 50));
        p.add(label, BorderLayout.WEST);
        p.add(field, BorderLayout.CENTER);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        return p;
    }

    private void handleSignup() {
        if (!agreeTerms.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "You must agree to the Terms & Conditions to sign up.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String info = String.format(
                "First Name: %s\nMiddle Name: %s\nLast Name: %s\nAge: %s\nAddress: %s\nGender: %s\nPassword: %s",
                firstNameField.getText(),
                middleNameField.getText(),
                lastNameField.getText(),
                ageField.getText(),
                addressField.getText(),
                genderBox.getSelectedItem(),
                new String(passwordField.getPassword())
        );

        JOptionPane.showMessageDialog(this,
                "Sign Up Successful!\n\n" + info,
                "Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EntryForm::new);
    }
}
