package DogPackage;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Signup_Login_GUI extends JFrame {
    private JTextField loginUserField, signupUserField;
    private JPasswordField loginPassField, signupPassField;
    private JLabel loginStatus, signupStatus;

    private static final String FILE_NAME = "/Users/makima/Desktop/untitled folder/School/src/DogPackage/new_users.txt";

    public Signup_Login_GUI() {
        super("Login & Sign Up System");
        initUI();
    }

    private void initUI() {
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // LOGIN TAB
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        loginUserField = new JTextField();
        loginPassField = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        loginStatus = new JLabel(" ", JLabel.CENTER);

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(loginUserField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(loginPassField);
        loginPanel.add(loginBtn);
        loginPanel.add(loginStatus);

        loginBtn.addActionListener(e -> checkLogin());

        JPanel signupPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        signupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        signupUserField = new JTextField();
        signupPassField = new JPasswordField();
        JButton signupBtn = new JButton("Sign Up");
        signupStatus = new JLabel(" ", JLabel.CENTER);

        signupPanel.add(new JLabel("New Username:"));
        signupPanel.add(signupUserField);
        signupPanel.add(new JLabel("New Password:"));
        signupPanel.add(signupPassField);
        signupPanel.add(signupBtn);
        signupPanel.add(signupStatus);

        signupBtn.addActionListener(e -> registerUser());

        tabbedPane.add("Login", loginPanel);
        tabbedPane.add("Sign Up", signupPanel);

        add(tabbedPane);
    }

    private void checkLogin() {
        String username = loginUserField.getText().trim();
        String password = new String(loginPassField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            loginStatus.setText("Fill in all fields");
            return;
        }

        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    String fileUser = parts[0].trim();
                    String filePass = parts[1].trim();
                    if (fileUser.equals(username) && filePass.equals(password)) {
                        found = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            loginStatus.setText("Error reading file");
            return;
        }

        if (found) {
            loginStatus.setText("Welcome, " + username + "!");
            JOptionPane.showMessageDialog(this, "Login Successful! Hello " + username);
        } else {
            loginStatus.setText("Invalid login");
        }
    }

    private void registerUser() {
        String username = signupUserField.getText().trim();
        String password = new String(signupPassField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            signupStatus.setText("Fill in all fields");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length > 0 && parts[0].trim().equals(username)) {
                    signupStatus.setText("Username already exists");
                    return;
                }
            }
        } catch (FileNotFoundException e) {
        
        } catch (IOException e) {
            signupStatus.setText("Error reading file");
            return;
        }

      
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(username + "," + password);
            bw.newLine();
            signupStatus.setText("User registered!");
            JOptionPane.showMessageDialog(this, "User " + username + " registered!");
        } catch (IOException e) {
            signupStatus.setText("Error writing file");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	Signup_Login_GUI gui = new Signup_Login_GUI();
            gui.setVisible(true);
        });
    }
}
