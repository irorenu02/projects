package Dawg;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class CampusFacilityBooking extends JFrame {

	private static final String FACILITIES_FILE = "/Users/makima/Desktop/untitled folder/School/src/Dawg/facilities.txt";
	private static final String BOOKINGS_FILE = "/Users/makima/Desktop/untitled folder/School/src/Dawg/booking.txt";
	private final FacilityManager facilityManager = new FacilityManager();
	private final BookingManager bookingManager = new BookingManager();
	private final DefaultTableModel facilityTableModel = new DefaultTableModel(
			new Object[] { "ID", "Facility", "Location", "Capacity", "Available" }, 0) {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private final JTable facilityTable = new JTable(facilityTableModel);
	private final DefaultTableModel bookingTableModel = new DefaultTableModel(
			new Object[] { "SID", "FID", "Facility", "Date", "Start", "End", "BookedBy" }, 0) {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private final JTable bookingTable = new JTable(bookingTableModel);
	private final JTextField locationFilter = new JTextField();
	private final JTextField minCapacityFilter = new JTextField();
	private final JComboBox<String> availabilityFilter = new JComboBox<>(
			new String[] { "Any", "Available", "Unavailable" });
	private final DateTimeFormatter dateFmt = DateTimeFormatter.ISO_LOCAL_DATE;
	private final DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

	private JLabel timeLabel;
	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public CampusFacilityBooking() {
		super("Campus Facility Booking System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(950, 675);
		setLocationRelativeTo(null);
		setResizable(false);

		initUI();

		facilityManager.loadFromFile(FACILITIES_FILE);
		bookingManager.loadFromFile(BOOKINGS_FILE);
		refreshFacilityTable();
		refreshBookingTable();
	}

	@SuppressWarnings("unused")
	private void initUI() {
		JPanel topP = new JPanel(new BorderLayout(8, 8));
		topP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.BLACK),
				new EmptyBorder(10, 10, 10, 10)));
		topP.setPreferredSize(new Dimension(800, 100));
		topP.setBackground(Color.WHITE);
		JLabel info = new JLabel("<html>  College of Computing Education" + "<br> Matina Campus, Davao City"
				+ "<br> Tel NO. 4008-666-666</html>");
		info.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		JPanel topLP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topLP.setBackground(Color.WHITE);
		topLP.add(info);
		ImageIcon icon = new ImageIcon(
				"/Users/makima/Desktop/untitled folder/logo.avif");
		Image scaled = icon.getImage().getScaledInstance(150, icon.getIconHeight() * 150 / icon.getIconWidth(),
				Image.SCALE_SMOOTH);
		JLabel imgLabel = new JLabel(new ImageIcon(scaled));
		JPanel topRP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topRP.setBackground(Color.WHITE);
		topRP.add(imgLabel);
		topP.add(topLP, BorderLayout.WEST);
		topP.add(topRP, BorderLayout.EAST);

		JPanel bottomP = new JPanel(new BorderLayout(8, 8));
		bottomP.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		bottomP.setPreferredSize(new Dimension(800, 500));
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setResizeWeight(0.55);
		bottomP.add(split, BorderLayout.CENTER);

		JPanel left = new JPanel(new BorderLayout(6, 6));
		JLabel fac = new JLabel("Facilities");
		fac.setFont(new Font(null, Font.BOLD, 16));
		fac.setBorder(new EmptyBorder(10, 10, 10, 0));
		left.add(fac, BorderLayout.NORTH);
		facilityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		left.add(new JScrollPane(facilityTable), BorderLayout.CENTER);
		JPanel facBottom = new JPanel(new GridLayout(2, 1, 6, 6));
		JPanel filters = new JPanel(new GridLayout(3, 2, 4, 4));
		filters.setBorder(BorderFactory.createTitledBorder("Filters"));
		filters.add(new JLabel("Location contains:"));
		filters.add(locationFilter);
		filters.add(new JLabel("Min capacity:"));
		filters.add(minCapacityFilter);
		filters.add(new JLabel("Availability:"));
		filters.add(availabilityFilter);
		facBottom.add(filters);
		JPanel facActions = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton addFac = new JButton("Add Facility");
		JButton editFac = new JButton("Edit Facility");
		JButton deleteFac = new JButton("Delete Facility");
		facActions.add(addFac);
		facActions.add(editFac);
		facActions.add(deleteFac);
		facBottom.add(facActions);
		left.add(facBottom, BorderLayout.SOUTH);
		split.setLeftComponent(left);

		JPanel right = new JPanel(new BorderLayout(6, 6));
		right.setPreferredSize(new Dimension(400, 400));
		JPanel timePanel = new JPanel(new BorderLayout());
		timeLabel = new JLabel();
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeLabel.setBorder(new EmptyBorder(10, 0, 10, 10));
		timePanel.add(timeLabel);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				SwingUtilities.invokeLater(() -> updateTime());
			}
		}, 0, 1000);
		right.add(timePanel, BorderLayout.NORTH);
		JScrollPane jsp = new JScrollPane(bookingTable);
		right.add(jsp, BorderLayout.CENTER);
		JPanel bookActions = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bookActions.setBorder(new EmptyBorder(5, 5, 175, 5));
		JButton newBookingBtn = new JButton("New Booking");
		JButton editBookingBtn = new JButton("Edit Booking");
		JButton cancelBookingBtn = new JButton("Cancel Booking");
		bookActions.add(newBookingBtn);
		bookActions.add(editBookingBtn);
		bookActions.add(cancelBookingBtn);
		right.add(bookActions, BorderLayout.SOUTH);
		split.setRightComponent(right);

		ActionListener filterAction = e -> refreshFacilityTable();
		locationFilter.addActionListener(filterAction);
		minCapacityFilter.addActionListener(filterAction);
		availabilityFilter.addActionListener(filterAction);
		locationFilter.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				refreshFacilityTable();
			}
		});
		minCapacityFilter.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				refreshFacilityTable();
			}
		});

		addFac.addActionListener(e -> showFacilityDialog(null));
		editFac.addActionListener(e -> {
			int r = facilityTable.getSelectedRow();
			if (r == -1) {
				JOptionPane.showMessageDialog(this, "Please select a facility to edit");
				return;
			}
			String id = facilityTableModel.getValueAt(r, 0).toString();
			Facility f = facilityManager.getById(id);
			showFacilityDialog(f);
		});
		deleteFac.addActionListener(e -> {
			int r = facilityTable.getSelectedRow();
			if (r == -1) {
				JOptionPane.showMessageDialog(this, "Please select a facility to delete");
				return;
			}
			String id = facilityTableModel.getValueAt(r, 0).toString();
			int ok = JOptionPane.showConfirmDialog(this,
					"Are you sure you want to delete this facility? This will also delete related bookings", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (ok == JOptionPane.YES_OPTION) {
				facilityManager.remove(id);
				bookingManager.removeByFacilityId(id);
				facilityManager.saveToFile(FACILITIES_FILE);
				bookingManager.saveToFile(BOOKINGS_FILE);
				refreshFacilityTable();
				refreshBookingTable();
			}
		});

		newBookingBtn.addActionListener(e -> showBookingDialog(null));
		editBookingBtn.addActionListener(e -> {
			int r = bookingTable.getSelectedRow();
			if (r == -1) {
				JOptionPane.showMessageDialog(this, "Please select a booking to edit");
				return;
			}
			String id = bookingTableModel.getValueAt(r, 0).toString();
			Booking b = bookingManager.getById(id);
			showBookingDialog(b);
		});
		cancelBookingBtn.addActionListener(e -> {
			int r = bookingTable.getSelectedRow();
			if (r == -1) {
				JOptionPane.showMessageDialog(this, "Please select a booking to cancel");
				return;
			}
			String id = bookingTableModel.getValueAt(r, 0).toString();
			int ok = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this booking?", "Confirm",
					JOptionPane.YES_NO_OPTION);
			if (ok == JOptionPane.YES_OPTION) {
				bookingManager.remove(id);
				bookingManager.saveToFile(BOOKINGS_FILE);
				refreshBookingTable();
			}
		});

		facilityTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())
					refreshBookingTable();
			}
		});

		final int[] lastSelectedRow = { -1 };

		facilityTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = facilityTable.rowAtPoint(e.getPoint());
				if (row != -1) {
					if (row == lastSelectedRow[0]) {
						facilityTable.clearSelection();
						lastSelectedRow[0] = -1;
					} else
						lastSelectedRow[0] = row;
				}
			}
		});

		JPanel rootP = new JPanel(new BorderLayout());
		rootP.add(topP, BorderLayout.NORTH);
		rootP.add(bottomP, BorderLayout.SOUTH);
		setContentPane(rootP);

		JMenuBar mb = new JMenuBar();
		JMenu option = new JMenu("Options(Alt+O)");
		option.setMnemonic(KeyEvent.VK_O);
		JMenuItem saveAll = new JMenuItem("Save(Ctrl+s)");
		JMenuItem refreshAll = new JMenuItem("Refresh(Ctrl+F5)");
		saveAll.addActionListener(e -> {
			facilityManager.saveToFile(FACILITIES_FILE);
			bookingManager.saveToFile(BOOKINGS_FILE);
			JOptionPane.showMessageDialog(this, "Data saved");
		});
		refreshAll.addActionListener(e -> {
			refreshBookingTable();
			refreshFacilityTable();
			JOptionPane.showMessageDialog(this, "Data refreshed");

		});
		option.add(saveAll);
		option.add(refreshAll);
		mb.add(option);
		setJMenuBar(mb);

		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control F5"),
				"refreshing");
		this.getRootPane().getActionMap().put("refreshing", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Data refreshed");
				refreshBookingTable();
			}
		});

		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control S"),
				"saving");
		this.getRootPane().getActionMap().put("saving", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				facilityManager.saveToFile(FACILITIES_FILE);
				bookingManager.saveToFile(BOOKINGS_FILE);
				JOptionPane.showMessageDialog(null, "Data saved");
				refreshBookingTable();
			}
		});
	}

	private void updateTime() {
		LocalTime now = LocalTime.now();
		LocalDate now2 = LocalDate.now();
		timeLabel.setFont(new Font(null, Font.BOLD, 16));
		timeLabel.setText(now2.format(dateFmt) + "    " + now.format(timeFormatter));
	}

	private void showFacilityDialog(Facility existing) {
		boolean editing = existing != null;
		JTextField nameF = new JTextField(editing ? existing.getFacility() : "");
		JTextField locF = new JTextField(editing ? existing.getLocation() : "");
		JTextField capF = new JTextField(editing ? String.valueOf(existing.getCapacity()) : "");
		JCheckBox availC = new JCheckBox("Available", editing ? existing.getAvailable() : true);

		JPanel p = new JPanel(new GridLayout(0, 1, 4, 4));
		p.add(new JLabel("Facility:"));
		p.add(nameF);
		p.add(new JLabel("Location:"));
		p.add(locF);
		p.add(new JLabel("Capacity:"));
		p.add(capF);
		p.add(availC);

		int ok = JOptionPane.showConfirmDialog(this, p, editing ? "Edit Facility" : "Add Facility",
				JOptionPane.OK_CANCEL_OPTION);
		if (ok == JOptionPane.OK_OPTION) {
			try {
				String name = nameF.getText().trim();
				String loc = locF.getText().trim();
				int cap = Integer.parseInt(capF.getText().trim());
				boolean avail = availC.isSelected();
				if (name.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Name cannot be empty");
					return;
				}
				if (editing) {
					existing.setName(name);
					existing.setLocation(loc);
					;
					existing.setCapacity(cap);
					;
					existing.setAvailable(avail);
					;
				} else {
					String newId = facilityManager.getNextId();
					Facility f = new Facility(newId, name, loc, cap, avail);
					facilityManager.add(f);
				}
				facilityManager.saveToFile(FACILITIES_FILE);
				refreshFacilityTable();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Capacity must be an integer");
			}
		}
	}

	private void showBookingDialog(Booking existing) {
		boolean editing = existing != null;
		List<Facility> facs = new ArrayList<>(facilityManager.getAll());
		if (facs.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No facilities available, please add a facility first");
			return;
		}
		String[] facNames = facs.stream().filter(Facility::isAvailable).map(f -> f.getId() + " - " + f.getFacility())
				.toArray(String[]::new);

		JComboBox<String> facCombo = new JComboBox<>(facNames);
		if (editing) {
			for (int i = 0; i < facs.size(); i++)
				if (facs.get(i).getId().equals(existing.getFacilityId()))
					facCombo.setSelectedIndex(i);
		}

		JTextField dateField = new JTextField(editing ? existing.getDate().toString() : LocalDate.now().toString());
		JTextField startField = new JTextField(editing ? existing.getStart().format(timeFmt) : "09:00");
		JTextField endField = new JTextField(editing ? existing.getEnd().format(timeFmt) : "10:00");
		JTextField byField = new JTextField(editing ? existing.getBookedBy() : "");

		JPanel p = new JPanel(new GridLayout(0, 1, 4, 4));
		p.add(new JLabel("Select Facility:"));
		p.add(facCombo);
		p.add(new JLabel("Date (YYYY-MM-DD):"));
		p.add(dateField);
		p.add(new JLabel("Start Time (HH:MM):"));
		p.add(startField);
		p.add(new JLabel("End Time (HH:MM):"));
		p.add(endField);
		p.add(new JLabel("Booked By:"));
		p.add(byField);

		int ok = JOptionPane.showConfirmDialog(this, p, editing ? "Edit Booking" : "New Booking",
				JOptionPane.OK_CANCEL_OPTION);
		if (ok == JOptionPane.OK_OPTION) {
			try {
				int idx = facCombo.getSelectedIndex();
				Facility selFac = facs.get(idx);
				LocalDate date = LocalDate.parse(dateField.getText().trim(), dateFmt);
				LocalTime start = LocalTime.parse(startField.getText().trim(), timeFmt);
				LocalTime end = LocalTime.parse(endField.getText().trim(), timeFmt);
				String by = byField.getText().trim();
				if (end.isBefore(start) || end.equals(start)) {
					JOptionPane.showMessageDialog(this, "End time must be later than start time");
					return;
				}

				Booking b;

				if (editing) {
					existing.setFacilityId(selFac.getId());
					
					existing.setDate(date);
					;
					existing.setStart(start);
					;
					existing.setEnd(end);
					;
					existing.setBookedBy(by);
					;
					b = existing;
				} else {
					String newId = bookingManager.getNextId();
					b = new Booking(newId, selFac.getId(), date, start, end, by);
				}

				String conflict = bookingManager.checkConflict(b);
				if (conflict != null) {
					JOptionPane.showMessageDialog(this, "Booking failed: " + conflict);
					return;
				}
				if (editing)
					bookingManager.update(b);
				else
					bookingManager.add(b);
				bookingManager.saveToFile(BOOKINGS_FILE);
				refreshBookingTable();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
			}
		}
	}

	private void refreshFacilityTable() {
		facilityTableModel.setRowCount(0);
		String loc = locationFilter.getText().trim().toLowerCase();
		int minCap = 0;
		try {
			if (!minCapacityFilter.getText().trim().isEmpty())
				minCap = Integer.parseInt(minCapacityFilter.getText().trim());
		} catch (Exception e) {
		}
		String avail = availabilityFilter.getSelectedItem().toString();
		for (Facility f : facilityManager.getAll()) {
			if (!loc.isEmpty() && !f.getLocation().toLowerCase().contains(loc)
					&& !f.getFacility().toLowerCase().contains(loc))
				continue;
			if (f.getCapacity() < minCap)
				continue;
			if (avail.equals("Available") && !f.getAvailable())
				continue;
			if (avail.equals("Unavailable") && f.getAvailable())
				continue;
			facilityTableModel.addRow(
					new Object[] { f.getId(), f.getFacility(), f.getLocation(), f.getCapacity(), f.getAvailable() });
		}
	}

	private void refreshBookingTable() {
		bookingTableModel.setRowCount(0);
		String selectedFacId = null;
		int sel = facilityTable.getSelectedRow();
		if (sel != -1)
			selectedFacId = facilityTableModel.getValueAt(sel, 0).toString();
		List<Booking> all = bookingManager.getAll();
		all.sort(Comparator.comparing((Booking b) -> b.getDate()).thenComparing(b -> b.getStart()));
		for (Booking b : all) {
			if (selectedFacId != null && !selectedFacId.equals(b.getFacilityId()))
				continue;
			Facility f = facilityManager.getById(b.getFacilityId());
			String fname = f == null ? "(Deleted)" : f.getFacility();
			bookingTableModel.addRow(new Object[] { b.getId(), b.getFacilityId(), fname, b.getDate().toString(),
					b.getStart().format(timeFmt), b.getEnd().format(timeFmt), b.getBookedBy() });
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new CampusFacilityBooking().setVisible(true));
	}
}
