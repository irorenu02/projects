import sys
from PyQt6.QtWidgets import QApplication, QDialog

from styles          import APP_STYLE
from laundry_manager import LaundryManager
from login_window    import LoginWindow
from main_window     import MainWindow

def main():
    app = QApplication(sys.argv)
    app.setStyleSheet(APP_STYLE)
    
    manager = LaundryManager()

    login = LoginWindow(manager)
    if login.exec() != QDialog.DialogCode.Accepted:
        sys.exit(0)

    window = MainWindow(manager)
    window.show()

    sys.exit(app.exec())


if __name__ == "__main__":
    main()
