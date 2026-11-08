# login screen / 3 attempts will close

from PyQt6.QtWidgets import QDialog, QVBoxLayout, QLabel, QLineEdit, QPushButton, QMessageBox
from PyQt6.QtCore import Qt
from styles import red_btn


class LoginWindow(QDialog):

    def __init__(self, manager):
        super().__init__()
        self.manager  = manager
        self.attempts = 0

        self.setWindowTitle("Login")
        self.setFixedSize(360, 300)
        self.setup_ui()

    def setup_ui(self):
        layout = QVBoxLayout()
        layout.setContentsMargins(30, 30, 30, 20)
        layout.setSpacing(10)

        # title
        title = QLabel("🧺 Laundry Monitoring System")
        title.setAlignment(Qt.AlignmentFlag.AlignCenter)
        title.setStyleSheet("font-size: 13pt; font-weight: bold; color: #2e86de;")
        layout.addWidget(title)

        sub = QLabel("Enter your credentials to continue")
        sub.setAlignment(Qt.AlignmentFlag.AlignCenter)
        sub.setStyleSheet("color: #aaaaaa; font-size: 9pt;")
        layout.addWidget(sub)

        # fields
        layout.addWidget(QLabel("Username:"))
        self.username_field = QLineEdit()
        self.username_field.setPlaceholderText("Enter username")
        layout.addWidget(self.username_field)

        layout.addWidget(QLabel("Password:"))
        self.password_field = QLineEdit()
        self.password_field.setPlaceholderText("Enter password")
        self.password_field.setEchoMode(QLineEdit.EchoMode.Password)
        layout.addWidget(self.password_field)

        # error message
        self.error_label = QLabel("")
        self.error_label.setStyleSheet("color: #2e86de;")
        self.error_label.setAlignment(Qt.AlignmentFlag.AlignCenter)
        layout.addWidget(self.error_label)

        # button
        login_btn = QPushButton("Login")
        login_btn.setStyleSheet(red_btn())
        login_btn.clicked.connect(self.check_login)
        layout.addWidget(login_btn)

        # press enter to log in
        self.password_field.returnPressed.connect(self.check_login)

        '''hint = QLabel("Hint: admin / adminrani123")
        hint.setAlignment(Qt.AlignmentFlag.AlignCenter)
        hint.setStyleSheet("color: #555; font-size: 8pt;")
        layout.addWidget(hint)'''

        self.setLayout(layout)

    def check_login(self):
        username = self.username_field.text().strip()
        password = self.password_field.text().strip()

        if self.manager.login(username, password):
            self.accept()
        else:
            self.attempts += 1
            remaining = 3 - self.attempts

            if remaining == 0:
                QMessageBox.critical(self, "Locked", "Too many failed attempts. Closing.")
                self.reject()
            else:
                self.error_label.setText(f"Incorrect credentials. {remaining} attempt(s) left.")
                self.password_field.clear()
                
