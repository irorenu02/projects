from PyQt6.QtWidgets import (
    QDialog, QVBoxLayout, QHBoxLayout,
    QLabel, QPushButton, QFrame
)
from PyQt6.QtCore import Qt
from styles import gray_btn


class ReportDialog(QDialog):

    def __init__(self, manager, parent=None):
        super().__init__(parent)
        self.manager = manager

        self.setWindowTitle("Laundry Shop Report")
        self.setFixedSize(380, 360)
        self.setup_ui()

    def setup_ui(self):
        layout = QVBoxLayout()
        layout.setContentsMargins(28, 24, 28, 24)
        layout.setSpacing(8)

        title = QLabel("📊 Laundry Shop Report")
        title.setAlignment(Qt.AlignmentFlag.AlignCenter)
        title.setStyleSheet("font-size: 13pt; font-weight: bold; color: #2e86de;")
        layout.addWidget(title)

        # get report data from manager
        data = self.manager.generate_report()

        report_rows = [
            ("Total Orders",  str(data["total"])),
            ("Completed",     str(data["completed"])),
            ("Pending",       str(data["pending"])),
            ("Washing",       str(data["washing"])),
            ("Drying",        str(data["drying"])),
            ("Total Revenue", f"PHP {data['revenue']:.2f}"),
        ]

        # build each row as a styled card
        for label_text, value_text in report_rows:
            row = QFrame()
            row.setStyleSheet("background-color: #0f3460; border-radius: 6px;")
            row_layout = QHBoxLayout(row)
            row_layout.setContentsMargins(14, 8, 14, 8)

            label = QLabel(label_text)
            label.setStyleSheet("color: #aaaaaa;")

            value = QLabel(value_text)
            value.setStyleSheet("color: white; font-weight: bold;")
            value.setAlignment(Qt.AlignmentFlag.AlignRight | Qt.AlignmentFlag.AlignVCenter)

            row_layout.addWidget(label)
            row_layout.addWidget(value)
            layout.addWidget(row)

        # close
        close_btn = QPushButton("Close")
        close_btn.setStyleSheet(gray_btn())
        close_btn.clicked.connect(self.accept)
        layout.addWidget(close_btn)

        self.setLayout(layout)
