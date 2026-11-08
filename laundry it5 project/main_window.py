# main screen / shows all the tables order

from PyQt6.QtWidgets import (
    QMainWindow, QWidget, QDialog,
    QVBoxLayout, QHBoxLayout,
    QLabel, QLineEdit, QPushButton,
    QTableWidget, QTableWidgetItem, QHeaderView,
    QMessageBox
)
from PyQt6.QtCore import Qt
from PyQt6.QtGui import QColor, QFont

from add_order_dialog import AddOrderDialog
from update_dialog    import UpdateDialog
from report_dialog    import ReportDialog
from styles           import red_btn, blue_btn, green_btn, dark_red_btn, gray_btn


class MainWindow(QMainWindow):

    def __init__(self, manager):
        super().__init__()
        self.manager = manager

        self.setWindowTitle("🧺 Laundry Monitoring and Services System")
        self.setMinimumSize(920, 580)
        self.setup_ui()
        self.load_table()

    def setup_ui(self):
        central = QWidget()
        self.setCentralWidget(central)

        main_layout = QVBoxLayout(central)
        main_layout.setContentsMargins(16, 16, 16, 12)
        main_layout.setSpacing(10)

        #header
        header = QLabel("🧺  Laundry Monitoring and Services System")
        header.setAlignment(Qt.AlignmentFlag.AlignCenter)
        header.setStyleSheet("""
            background-color: #2e86de;
            color: white;
            font-size: 14pt;
            font-weight: bold;
            padding: 12px;
            border-radius: 8px;
        """)
        main_layout.addWidget(header)

        #search bar
        search_row = QHBoxLayout()

        search_label = QLabel("🔍 Search:")
        search_label.setStyleSheet("color: #aaaaaa;")

        self.search_field = QLineEdit()
        self.search_field.setPlaceholderText("Search by customer name or order ID...")
        self.search_field.textChanged.connect(self.load_table)

        clear_btn = QPushButton("Clear")
        clear_btn.setStyleSheet(gray_btn())
        clear_btn.setFixedWidth(70)
        clear_btn.clicked.connect(lambda: self.search_field.clear())

        search_row.addWidget(search_label)
        search_row.addWidget(self.search_field)
        search_row.addWidget(clear_btn)
        main_layout.addLayout(search_row)

        #order table
        self.table = QTableWidget()
        self.table.setColumnCount(6)
        self.table.setHorizontalHeaderLabels(["ID", "Name", "Service", "Weight (kg)", "Price", "Status"])
        self.table.setEditTriggers(QTableWidget.EditTrigger.NoEditTriggers)
        self.table.setSelectionBehavior(QTableWidget.SelectionBehavior.SelectRows)
        self.table.setSelectionMode(QTableWidget.SelectionMode.SingleSelection)
        self.table.verticalHeader().setVisible(False)
        self.table.horizontalHeader().setSectionResizeMode(1, QHeaderView.ResizeMode.Stretch)
        self.table.setAlternatingRowColors(True)
        self.table.setStyleSheet("""
            QTableWidget { background-color: #0f3460; color: white; gridline-color: #1a1a2e; border-radius: 8px; }
            QTableWidget::item { padding: 4px; }
            QTableWidget::item:selected { background-color: #2e86de; }
            QTableWidget::item:alternate { background-color: #12285a; }
            QHeaderView::section { background-color: #2e86de; color: white; font-weight: bold; padding: 6px; border: none; }
        """)
        main_layout.addWidget(self.table)

        # action button
        btn_row = QHBoxLayout()
        btn_row.setSpacing(8)

        add_btn = QPushButton("➕ Add Order")
        add_btn.setStyleSheet(red_btn())
        add_btn.clicked.connect(self.open_add_dialog)

        update_btn = QPushButton("✏️ Update Status")
        update_btn.setStyleSheet(blue_btn())
        update_btn.clicked.connect(self.open_update_dialog)

        delete_btn = QPushButton("🗑️ Delete Order")
        delete_btn.setStyleSheet(dark_red_btn())
        delete_btn.clicked.connect(self.delete_order)

        report_btn = QPushButton("📊 Generate Report")
        report_btn.setStyleSheet(green_btn())
        report_btn.clicked.connect(self.open_report_dialog)

        btn_row.addWidget(add_btn)
        btn_row.addWidget(update_btn)
        btn_row.addWidget(delete_btn)
        btn_row.addWidget(report_btn)
        btn_row.addStretch()
        main_layout.addLayout(btn_row)

        # status label
        self.status_label = QLabel("Ready.")
        self.status_label.setStyleSheet("color: #aaaaaa; font-size: 9pt;")
        main_layout.addWidget(self.status_label)

    # loads/refresh the table (can search also)
    def load_table(self):
        keyword = self.search_field.text().strip()

        if keyword == "":
            records = self.manager.records
        else:
            records = self.manager.search_orders(keyword)

        self.table.setRowCount(0)

        for order in records:
            row = self.table.rowCount()
            self.table.insertRow(row)

            self.table.setItem(row, 0, QTableWidgetItem(str(order.order_id)))
            self.table.setItem(row, 1, QTableWidgetItem(order.name))
            self.table.setItem(row, 2, QTableWidgetItem(order.service))
            self.table.setItem(row, 3, QTableWidgetItem(str(order.weight)))
            self.table.setItem(row, 4, QTableWidgetItem(f"PHP {order.price:.2f}"))

            # color coded status
            status_item = QTableWidgetItem(order.status)
            status_item.setFont(QFont("Segoe UI", 9, QFont.Weight.Bold))

            if order.status == "Pending":
                status_item.setForeground(QColor("#f39c12"))
            elif order.status == "Washing":
                status_item.setForeground(QColor("#3498db"))
            elif order.status == "Drying":
                status_item.setForeground(QColor("#9b59b6"))
            elif order.status == "Completed":
                status_item.setForeground(QColor("#2ecc71"))

            self.table.setItem(row, 5, status_item)

            for col in range(6):
                item = self.table.item(row, col)
                if item:
                    item.setTextAlignment(Qt.AlignmentFlag.AlignCenter)

        self.status_label.setText(f"{len(records)} record(s) shown.")

    # returns the currently selected order subject
    def get_selected_order(self):
        selected_row = self.table.currentRow()

        if selected_row < 0:
            QMessageBox.warning(self, "No Selection", "Please select an order from the table first.")
            return None

        order_id = int(self.table.item(selected_row, 0).text())
        return self.manager.find_order(order_id)

    # opens the AddOrderDialog
    def open_add_dialog(self):
        dialog = AddOrderDialog(self.manager, parent=self)
        dialog.exec()

        if dialog.added_order:
            self.load_table()
            self.status_label.setText(
                f"Order #{dialog.added_order.order_id} added for {dialog.added_order.name}."
            )

    # opens UpdateDialog
    def open_update_dialog(self):
        order = self.get_selected_order()
        if order is None:
            return

        dialog = UpdateDialog(order, self.manager, parent=self)
        dialog.exec()

        if dialog.result() == QDialog.DialogCode.Accepted:
            self.load_table()
            self.status_label.setText(f"Order #{order.order_id} status updated.")

    # deletes selected order after confirmation
    def delete_order(self):
        order = self.get_selected_order()
        if order is None:
            return

        reply = QMessageBox.question(
            self,
            "Confirm Delete",
            f"Delete Order #{order.order_id} for '{order.name}'?",
            QMessageBox.StandardButton.Yes | QMessageBox.StandardButton.No
        )

        if reply == QMessageBox.StandardButton.Yes:
            self.manager.delete_order(order.order_id)
            self.load_table()
            self.status_label.setText(f"Order #{order.order_id} deleted.")

    # opens ReportDialog
    def open_report_dialog(self):
        dialog = ReportDialog(self.manager, parent=self)
        dialog.exec()
