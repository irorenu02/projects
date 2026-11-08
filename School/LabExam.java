package School;

import javax.swing.*;
import java.awt.*;

public class LabExam {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConverterFrame::new);
    }
}

class ConverterFrame extends JFrame {
    private JTextField inputField;
    private JTextField resultField;
    private JComboBox<String> fromBox, toBox;

    public ConverterFrame() {
        setTitle("Number System Converter");
        setSize(420, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 

        inputField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        String[] bases = {"Binary (2)", "Octal (8)", "Decimal (10)", "Hexadecimal (16)"};
        fromBox = new JComboBox<>(bases);
        toBox = new JComboBox<>(bases);

        JButton convertBtn = new JButton("Convert");
        convertBtn.setFocusable(false);

        convertBtn.addActionListener(e -> convert());

        mainPanel.add(new JLabel("Input Number:"));
        mainPanel.add(inputField);

        mainPanel.add(new JLabel("Base From:"));
        mainPanel.add(fromBox);

        mainPanel.add(new JLabel("Base To:"));
        mainPanel.add(toBox);

        mainPanel.add(new JLabel("Result:"));
        mainPanel.add(resultField);

        mainPanel.add(new JLabel(""));
        mainPanel.add(convertBtn);

        add(mainPanel);

        setVisible(true);
    }

    private void convert() {
        try {
            String number = inputField.getText().trim();
            int baseFrom = getBase(fromBox.getSelectedIndex());
            int baseTo = getBase(toBox.getSelectedIndex());

            int decimalValue = Integer.parseInt(number, baseFrom);
            String result = Integer.toString(decimalValue, baseTo).toUpperCase();

            resultField.setText(result);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input or number does not match the base.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getBase(int index) {
        return switch (index) {
            case 0 -> 2;
            case 1 -> 8;
            case 2 -> 10;
            case 3 -> 16;
            default -> 10;
        };
    }
}
