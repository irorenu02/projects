import sys
from PyQt6.QtWidgets import (
    QApplication, QWidget, QLabel,
    QLineEdit, QPushButton, QVBoxLayout
)

class StudentGreetingApp(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Student Greeting App")
        self.setMinimumWidth(300)

        layout = QVBoxLayout()

        self.name_label = QLabel("Enter your name:")
        self.name_input = QLineEdit()

        self.course_label = QLabel("Enter your course:")
        self.course_input = QLineEdit()

        self.submit_btn = QPushButton("Submit")
        self.submit_btn.clicked.connect(self.greet)

        self.result_label = QLabel("")

        layout.addWidget(self.name_label)
        layout.addWidget(self.name_input)
        layout.addWidget(self.course_label)
        layout.addWidget(self.course_input)
        layout.addWidget(self.submit_btn)
        layout.addWidget(self.result_label)

        self.setLayout(layout)

    def greet(self):
        name = self.name_input.text()
        course = self.course_input.text()
        self.result_label.setText(f"Hello, {name} from {course}!")

app = QApplication(sys.argv)
window = StudentGreetingApp()
window.show()
sys.exit(app.exec())