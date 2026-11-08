package Neyan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GateEntryMonitoringSystem extends JFrame {

	private static final String table_file = "/Users/makima/Desktop/untitled folder/School/src/Neyan/CSVtable.txt";
	private final sysTable ST = new sysTable();

	private final DefaultTableModel dtm = new DefaultTableModel(
			new Object[] { "ID", "First Name", "Last Name", "Age", "Gender", "Purpose" }, 0) {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	private final JTable entryTable = new JTable(dtm);

	public GateEntryMonitoringSystem() {
		super("Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 600);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

		GUI();

		entryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ST.loadFile(table_file);
		F5T();
	}

	public void GUI() {
		JLabel title = new JLabel("Gate Entry Monitoring System");
		title.setBorder(new EmptyBorder(5, 60, 5, 5));
		title.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

		JScrollPane table = new JScrollPane(entryTable);
		JPanel tablePanel = new JPanel(new BorderLayout(8, 8));
		tablePanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		;
		tablePanel.add(table);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.setBorder(new EmptyBorder(30, 90, 30, 0));
		JButton add = new JButton("Add");
		JButton edit = new JButton("Edit");
		JButton del = new JButton("Delete");
		JButton clr = new JButton("Clear");
		buttonPanel.add(add);
		buttonPanel.add(edit);
		buttonPanel.add(del);
		buttonPanel.add(clr);

		JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.add(title, BorderLayout.NORTH);
		mainPanel.add(tablePanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		add.addActionListener(e -> tableAction(null));

		edit.addActionListener(e -> {
			int row = entryTable.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Select row");
				return;
			}
			String id = dtm.getValueAt(row, 0).toString().trim();
			entryProperties ep = ST.getByID(id);
			tableAction(ep);

		});

		del.addActionListener(e -> {
			int row = entryTable.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Select row");
				return;
			}
			String id = dtm.getValueAt(row, 0).toString().trim();
			ST.remove(id);
			ST.saveFile(table_file);
			F5T();

		});
		
		clr.addActionListener(e -> {
			ST.clearAll();
			ST.saveFile(table_file);
			F5T();
		});
		add(mainPanel);
	}

	public void tableAction(entryProperties exists) {
		boolean edit = exists != null;

		JTextField fn = new JTextField(edit ? exists.getFirstName() : "");
		JTextField ln = new JTextField(edit ? exists.getLastName() : "");
		JTextField a = new JTextField(edit ? String.valueOf(exists.getAge()) : "");

		JRadioButton m = new JRadioButton("Male");
		JRadioButton f = new JRadioButton("Female");
		ButtonGroup g = new ButtonGroup();
		g.add(m);
		g.add(f);

		if (edit) {
			if ("Male".equalsIgnoreCase(exists.getGender()))
				m.setSelected(true);
			else if ("Female".equalsIgnoreCase(exists.getGender()))
				f.setSelected(true);
		}
		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		genderPanel.add(m);
		genderPanel.add(f);

		String[] pur = { "Visitation", "Leisure", "Property Purchase", "Others" };
		JComboBox<String> p = new JComboBox<String>(pur);
		if (edit)
			p.setSelectedItem(exists.getPurpose());

		JPanel inputPanel = new JPanel(new GridLayout(5, 2, 6, 6));
		inputPanel.add(new JLabel("First Name:"));
		inputPanel.add(fn);
		inputPanel.add(new JLabel("Last Name:"));
		inputPanel.add(ln);
		inputPanel.add(new JLabel("Age:"));
		inputPanel.add(a);
		inputPanel.add(new JLabel("Gender:"));
		inputPanel.add(genderPanel);
		inputPanel.add(new JLabel("Purpose:"));
		inputPanel.add(p);

		int ok = JOptionPane.showConfirmDialog(this, inputPanel, "Input", JOptionPane.OK_CANCEL_OPTION);
		if (ok == JOptionPane.OK_OPTION) {
			String firstName = fn.getText().trim();
			String lastName = ln.getText().trim();
			int age = Integer.parseInt(a.getText().trim());
			String gender = "";
			for (AbstractButton b : java.util.Collections.list(g.getElements())) {
				if (b.isSelected()) {
					gender = b.getText();
				}
			}
			String purpose = p.getSelectedItem().toString();

			if (edit) {
				exists.setFirstName(firstName);
				exists.setLastName(lastName);
				exists.setAge(age);
				exists.setGender(gender);
				exists.setPurpose(purpose);
			} else {
				entryProperties e = new entryProperties(ST.getNextID(), firstName, lastName, age, gender, purpose);
				ST.addUpdate(e);

			}

		}
		F5T();
		ST.saveFile(table_file);
	}

	public void F5T() {
		dtm.setRowCount(0);

		for (entryProperties e : ST.getAll()) {
			dtm.addRow(new Object[] { e.getID(), e.getFirstName(), e.getLastName(), e.getAge(), e.getGender(),
					e.getPurpose() });
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GateEntryMonitoringSystem());
	}

}
