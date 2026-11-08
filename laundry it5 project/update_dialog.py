# to change the status of an order

from PyQt6.QtWidgets import (
    QDialog, QVBoxLayout, QHBoxLayout,
    QLabel, QPushButton, QComboBox
)
from styles import blue_btn, gray_btn


class UpdateDialog(QDialog):

    def __init__(self, order, manager, parent=None):
        super().__init__(parent)
        self.order   = order
        self.manager = manager

        self.setWindowTitle(f"Update Order #{order.order_id}")
        self.setFixedSize(320, 180)
        self.setup_ui()

    def setup_ui(self):
        layout = QVBoxLayout()
        layout.setContentsMargins(24, 20, 24, 20)
        layout.setSpacing(10)

        info = QLabel(f"Order #{self.order.order_id}  |  {self.order.name}")
        info.setStyleSheet("font-size: 11pt; font-weight: bold; color: #2e86de;")
        layout.addWidget(info)

        layout.addWidget(QLabel("Select new status:"))

        self.status_combo = QComboBox()
        self.status_combo.addItems(["Pending", "Washing", "Drying", "Completed"])
        self.status_combo.setCurrentText(self.order.status)
        layout.addWidget(self.status_combo)

        btn_row = QHBoxLayout()

        apply_btn = QPushButton("Apply")
        apply_btn.setStyleSheet(blue_btn())
        apply_btn.clicked.connect(self.apply_update)

        cancel_btn = QPushButton("Cancel")
        cancel_btn.setStyleSheet(gray_btn())
        cancel_btn.clicked.connect(self.reject)

        btn_row.addWidget(apply_btn)
        btn_row.addWidget(cancel_btn)
        layout.addLayout(btn_row)

        self.setLayout(layout)

    def apply_update(self):
        new_status = self.status_combo.currentText()
        self.manager.update_status(self.order.order_id, new_status)
        self.accept()
