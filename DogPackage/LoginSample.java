package DogPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginNoLayout extends JFrame {
    public LoginNoLayout() {
        setTitle("Login - No Layout");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 80, 25);
        add(userLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(140, 50, 150, 25);
        add(usernameField);
        usernameField.setForeground(Color.black);
        usernameField.setBackground(Color.white);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 90, 80, 25);
        add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(140, 90, 150, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 130, 100, 25);
        add(loginButton);

        setVisible(true);
    }
}

//  FlowLayout
class LoginFlowLayout extends JFrame {
    public LoginFlowLayout() {
        setTitle("Login - FlowLayout");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Try changing FlowLayout.LEFT, FlowLayout.CENTER, FlowLayout.RIGHT
		 setLayout(new FlowLayout(FlowLayout.CENTER)); 

        add(new JLabel("Username:"));
        add(new JTextField(15));
        add(new JLabel("Password:"));
        add(new JPasswordField(15));
        add(new JButton("Login"));

        setVisible(true);
    }
}


    

// GridLayout
class LoginGridLayout extends JFrame {
    public LoginGridLayout() {
        setTitle("Login - GridLayout");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Username:"));
        add(new JTextField(15));
        add(new JLabel("Password:"));
        add(new JPasswordField(15));
        add(new JLabel());
        add(new JButton("Login"));

        setVisible(true);
    }
}

// BorderLayout
class LoginBorderLayout extends JFrame {
    public LoginBorderLayout() {
        setTitle("Login - BorderLayout");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(2, 2));
        centerPanel.add(new JLabel("Username:"));
        centerPanel.add(new JTextField(15));
        centerPanel.add(new JLabel("Password:"));
        centerPanel.add(new JPasswordField(15));     
        add(centerPanel, BorderLayout.CENTER);
        
        add(new JButton("South"), BorderLayout.SOUTH);
        add(new JButton("North"), BorderLayout.NORTH);
        add(new JButton("East"), BorderLayout.EAST);
        add(new JButton("West"), BorderLayout.WEST);

        setVisible(true);
    }
}

// CardLayout
class LoginCardLayout extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public LoginCardLayout() {
        setTitle("Login - CardLayout");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(new JTextField(15));
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(new JPasswordField(15));
        JButton switchToSignup = new JButton("Sign Up");
        loginPanel.add(new JButton("Login"));
        loginPanel.add(switchToSignup);

        JPanel signupPanel = new JPanel(new GridLayout(4, 2));
        signupPanel.add(new JLabel("Full Name:"));
        signupPanel.add(new JTextField(15));
        signupPanel.add(new JLabel("Email:"));
        signupPanel.add(new JTextField(15));
        signupPanel.add(new JLabel("Password:"));
        signupPanel.add(new JPasswordField(15));
        JButton switchToLogin = new JButton("Back to Login");
        signupPanel.add(switchToLogin);

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(signupPanel, "Signup");

        switchToSignup.addActionListener(e -> cardLayout.show(cardPanel, "Signup"));
        switchToLogin.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        add(cardPanel);
        setVisible(true);
    }
}

// Main Class to Run Different Layout Examples
public class LoginSample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
			new LoginNoLayout(); 
			/* new LoginFlowLayout(); */
			/* new LoginGridLayout(); */
			/* new LoginBorderLayout(); */
			/* new LoginCardLayout(); */
        });
    }
}