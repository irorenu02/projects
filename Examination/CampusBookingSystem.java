package Examination;

import javax.swing.*; 
import javax.swing.table.DefaultTableModel;
import java.awt.*; 
import java.time.LocalDate;
import java.time.LocalTime; 
import java.time.format.DateTimeFormatter;

public class CampusBookingSystem extends JFrame {
    private JComboBox<String> facilityCombo;
    private JTextField userField, dateField, startField, endField;
    private JButton bookBtn, saveBtn, cancelBtn, clearBtn;
    private JTable bookingTable;
    private DefaultTableModel tableModel;

    private BookingManager bookingManager;

    public CampusBookingSystem() {
        super("Campus Facility Booking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bookingManager = new BookingManager();

        initUI();
        loadBookings();
    }

    private void initUI() {
        // Use Nimbus Look & Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        // 🔹 Title Label
        JLabel title = new JLabel("Campus Facility Booking System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // 🔹 Form Panel (with padding)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Booking Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        facilityCombo = new JComboBox<>(new String[]{"Gym", "Library", "Auditorium", "Laboratory"});
        userField = new JTextField();
        dateField = new JTextField("2025-09-25");
        startField = new JTextField("09:00");
        endField = new JTextField("10:00");

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Facility:"), gbc);
        gbc.gridx = 1; formPanel.add(facilityCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("User:"), gbc);
        gbc.gridx = 1; formPanel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Date (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1; formPanel.add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(new JLabel("Start (HH:mm):"), gbc);
        gbc.gridx = 1; formPanel.add(startField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(new JLabel("End (HH:mm):"), gbc);
        gbc.gridx = 1; formPanel.add(endField, gbc);

        // 🔹 Buttons
        bookBtn = new JButton("Book");
        saveBtn = new JButton("Save");
        cancelBtn = new JButton("Cancel");
        clearBtn = new JButton("Clear");

        // Button colors
        bookBtn.setBackground(new Color(76, 175, 80));   // Green
        bookBtn.setForeground(Color.WHITE);
        saveBtn.setBackground(new Color(33, 150, 243));  // Blue
        saveBtn.setForeground(Color.WHITE);
        cancelBtn.setBackground(new Color(244, 67, 54)); // Red
        cancelBtn.setForeground(Color.WHITE);
        clearBtn.setBackground(new Color(158, 158, 158)); // Gray
        clearBtn.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bookBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(clearBtn);

        // 🔹 Table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Facility", "User", "Date", "Start", "End"}, 0);
        bookingTable = new JTable(tableModel);
        bookingTable.setRowHeight(25);
        bookingTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        bookingTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane tableScroll = new JScrollPane(bookingTable);

        // 🔹 Layout
        setLayout(new BorderLayout(10, 10));
        add(title, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(1000, 600);
        setLocationRelativeTo(null);

        // 🔹 Button Actions
        bookBtn.addActionListener(e -> addBooking());
        saveBtn.addActionListener(e -> saveBookings());
        cancelBtn.addActionListener(e -> cancelBooking());
        clearBtn.addActionListener(e -> clearForm());
    }

    private void addBooking() {
        try {
            String facility = (String) facilityCombo.getSelectedItem();
            String user = userField.getText().trim();
            LocalDate date = LocalDate.parse(dateField.getText().trim());
            LocalTime start = LocalTime.parse(startField.getText().trim());
            LocalTime end = LocalTime.parse(endField.getText().trim());

            Booking booking = new Booking(facility, user, date, start, end);
            bookingManager.addBooking(booking);
            refreshTable();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    private void cancelBooking() {
        int row = bookingTable.getSelectedRow();
        if (row >= 0) {
            int id = (int) tableModel.getValueAt(row, 0);
            bookingManager.removeBooking(id);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Select a booking to cancel.");
        }
    }

    private void saveBookings() {
        try {
            bookingManager.saveToFile(BookingManager.DEFAULT_FILE);
            JOptionPane.showMessageDialog(this, "Bookings saved successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage());
        }
    }

    private void clearForm() {
        userField.setText("");
        dateField.setText("2025-09-25");
        startField.setText("09:00");
        endField.setText("10:00");
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");

        for (Booking b : bookingManager.getAllBookings()) {
            tableModel.addRow(new Object[]{
                    b.getId(),
                    b.getFacility(),
                    b.getUser(),
                    b.getDate().format(dtf),
                    b.getStart().format(tf),
                    b.getEnd().format(tf)
            });
        }
    }

    private void loadBookings() {
        try {
            bookingManager.loadFromFile(BookingManager.DEFAULT_FILE);
            refreshTable();
        } catch (Exception ignored) { }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CampusBookingSystem().setVisible(true));
    }
}
