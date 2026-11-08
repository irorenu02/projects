package DogPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username: "), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Password: "), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1; gbc.gridy = 2;
        add(loginButton, gbc);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setForeground(Color.BLUE);
        signUpButton.setBorderPainted(false);
        signUpButton.setContentAreaFilled(false);
        signUpButton.setFocusPainted(false);
        gbc.gridx = 1; gbc.gridy = 3;
        add(signUpButton, gbc);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("Feranz") && password.equals("laminamuuli")) {
                    JOptionPane.showMessageDialog(Login.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); 
                  
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid Credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

  
        signUpButton.addActionListener(e -> {
            dispose(); 
            new Login().setVisible(true);
        });

        setVisible(true);
    }
}