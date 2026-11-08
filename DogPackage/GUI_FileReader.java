package DogPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI_FileReader extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JLabel statusLabel;

    public GUI_FileReader() {
        super("Login System with FileReader");
        initUI();
    }

    private void initUI() {
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Username:"));
        userField = new JTextField();
        formPanel.add(userField);

        formPanel.add(new JLabel("Password:"));
        passField = new JPasswordField();
        formPanel.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> checkLogin());

        statusLabel = new JLabel("Enter your credentials", JLabel.CENTER);

        add(formPanel, BorderLayout.CENTER);
        add(loginBtn, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);
    }

    private void checkLogin() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        boolean success = false;

        // mubasa sa users.txt
        try (FileReader fr = new FileReader("/Users/makima/Desktop/untitled folder/School/src/DogPackage/users.txt");
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String fileUser = parts[0].trim();
                    String filePass = parts[1].trim();

                    if (fileUser.equals(username) && filePass.equals(password)) {
                        success = true;
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error reading users.txt: " + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (success) {
            statusLabel.setText("✅ yeyyyy omg login successful! Welcome " + username);
            JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");
        } else {
            statusLabel.setText("❌ ngiii, invalid username and password");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI_FileReader app = new GUI_FileReader();
            app.setVisible(true);
        });
    }
}
