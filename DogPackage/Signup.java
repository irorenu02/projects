package DogPackage;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Signup {
    public static void main(String[] args) {
        JFrame setA = new JFrame();
        setA.setSize(500, 700);
        setA.setResizable(false);
        setA.setTitle("Autentico - Set A");
        setA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setA.setLayout(null);

        
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.LIGHT_GRAY);
        panel1.setBounds(0, 0, 500, 560);
        panel1.setLayout(null);

        
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.YELLOW);
        panel2.setBounds(0, 560, 500, 140);
        panel2.setLayout(null);

        
        JTextArea ad = new JTextArea("JOIN WITH YOUR EMAIL ADDRESS\nIt's amazing, it's free!");
        ad.setBackground(Color.LIGHT_GRAY);
        ad.setFont(new Font(null, Font.BOLD, 12));
        ad.setBounds(10, 0, 480, 30);

        
        JLabel name = new JLabel("FULL NAME:");
        name.setFont(new Font(null, Font.BOLD, 12));
        name.setBounds(10, 40, 200, 20);
        JTextField nameIN = new JTextField();
        nameIN.setBounds(10, 65, 450, 30);

        
        JLabel gender = new JLabel("GENDER:");
        gender.setFont(new Font(null, Font.BOLD, 12));
        gender.setBounds(10, 105, 200, 20);
        String[] genders = {"Man", "Woman", "Trans"};
        JComboBox<String> genderbox = new JComboBox<>(genders);
        genderbox.setBounds(10, 130, 150, 30);

       
        JLabel uname = new JLabel("USERNAME:");
        uname.setFont(new Font(null, Font.BOLD, 12));
        uname.setBounds(10, 170, 200, 20);
        JTextField unameIN = new JTextField();
        unameIN.setBounds(10, 195, 450, 30);

        JLabel pass = new JLabel("PASSWORD:");
        pass.setFont(new Font(null, Font.BOLD, 12));
        pass.setBounds(10, 235, 200, 20);
        JPasswordField passIN = new JPasswordField();
        passIN.setBounds(10, 260, 450, 30);

        JLabel email = new JLabel("E-MAIL ADDRESS:");
        email.setFont(new Font(null, Font.BOLD, 12));
        email.setBounds(10, 300, 200, 20);
        JTextField emailIN = new JTextField();
        emailIN.setBounds(10, 325, 450, 30);

        JLabel birthlabel = new JLabel("DATE OF BIRTH:");
        birthlabel.setFont(new Font(null, Font.BOLD, 12));
        birthlabel.setBounds(10, 365, 200, 20);
        String[] months = {"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE",
                           "JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);
        String[] years = {"2000","2001","2002","2003","2004","2005","2006","2007",
                          "2008","2009","2010","2011","2012","2013","2014","2015",
                          "2016","2017","2018","2019","2020","2021","2022","2023",
                          "2024","2025"};
        
        JComboBox<String> monthbox = new JComboBox<>(months);
        monthbox.setBounds(10, 390, 120, 30);
        JComboBox<String> daybox = new JComboBox<>(days);
        daybox.setBounds(140, 390, 80, 30);
        JComboBox<String> yearbox = new JComboBox<>(years);
        yearbox.setBounds(230, 390, 100, 30);

        JCheckBox check = new JCheckBox();
        check.setBackground(Color.LIGHT_GRAY);
        check.setBounds(10, 430, 20, 20);
        JLabel terms = new JLabel("I accept the terms of services.");
        terms.setBounds(35, 430, 250, 20);

        JButton button = new JButton("SIGN UP");
        button.setBounds(190, 40, 120, 40);
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!check.isSelected()) {
                    JOptionPane.showMessageDialog(setA, "You must accept the terms of services!");
                } else {
                    String info = "Full Name: " + nameIN.getText() + "\n"
                                + "Gender: " + genderbox.getSelectedItem() + "\n"
                                + "Username: " + unameIN.getText() + "\n"
                                + "Password: " + new String(passIN.getPassword()) + "\n"
                                + "Email: " + emailIN.getText() + "\n"
                                + "Date of Birth: " + daybox.getSelectedItem() + " / "
                                + monthbox.getSelectedItem() + " / "
                                + yearbox.getSelectedItem();
                    JOptionPane.showMessageDialog(setA, info, "Registered Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        panel1.add(ad);
        panel1.add(name);
        panel1.add(nameIN);
        panel1.add(gender);
        panel1.add(genderbox);
        panel1.add(uname);
        panel1.add(unameIN);
        panel1.add(pass);
        panel1.add(passIN);
        panel1.add(email);
        panel1.add(emailIN);
        panel1.add(birthlabel);
        panel1.add(monthbox);
        panel1.add(daybox);
        panel1.add(yearbox);
        panel1.add(check);
        panel1.add(terms);
        panel2.add(button);

        setA.add(panel1);
        setA.add(panel2);
        setA.setVisible(true);
    }
}
