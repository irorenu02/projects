# form dialog where staff enters new order details

from PyQt6.QtWidgets import (
    QDialog, QVBoxLayout, QHBoxLayout, QFormLayout,
    QLabel, QLineEdit, QPushButton, QComboBox, QMessageBox
)
from styles import red_btn, gray_btn


class AddOrderDialog(QDialog):

    def __init__(self, manager, parent=None):
        super().__init__(parent)
        self.manager     = manager
        self.added_order = None   #stores the new order if successful

        self.setWindowTitle("Add New Order")
        self.setFixedSize(380, 240)
        self.setup_ui()

    def setup_ui(self):
        layout = QVBoxLayout()
        layout.setContentsMargins(24, 20, 24, 20)
        layout.setSpacing(10)

        title = QLabel("➕ Add New Order")
        title.setStyleSheet("font-size: 11pt; font-weight: bold; color: #2e86de;")
        layout.addWidget(title)

        # form
        form = QFormLayout()
        form.setSpacing(8)

        self.name_field = QLineEdit()
        self.name_field.setPlaceholderText("e.g. Juan Dela Cruz")
        form.addRow("Customer Name:", self.name_field)

        self.service_combo = QComboBox()
        self.service_combo.addItems(["Wash", "Dry", "Fold", "Wash/Dry/Fold"])
        form.addRow("Service Type:", self.service_combo)

        self.weight_field = QLineEdit()
        self.weight_field.setPlaceholderText("e.g. 3.5")
        form.addRow("Weight (kg):", self.weight_field)

        layout.addLayout(form)

        # buttons
        btn_row = QHBoxLayout()

        add_btn = QPushButton("Add Order")
        add_btn.setStyleSheet(red_btn())
        add_btn.clicked.connect(self.submit)

        cancel_btn = QPushButton("Cancel")
        cancel_btn.setStyleSheet(gray_btn())
        cancel_btn.clicked.connect(self.reject)

        btn_row.addWidget(add_btn)
        btn_row.addWidget(cancel_btn)
        layout.addLayout(btn_row)

        self.setLayout(layout)

    def submit(self):
        name       = self.name_field.text().strip()
        service    = self.service_combo.currentText()
        weight_str = self.weight_field.text().strip()

        # ivalidate ang input
        if name == "":
            QMessageBox.warning(self, "Missing", "Please enter the customer name.")
            return

        try:
            weight = float(weight_str)
            if weight <= 0:
                raise ValueError
        except ValueError:
            QMessageBox.warning(self, "Invalid", "Please enter a valid weight (e.g. 3.5).")
            return

        # save via manager
        self.added_order = self.manager.add_order(name, service, weight)
        self.accept()
