# wala style style lang — cool blue theme

APP_STYLE = """
    QWidget     { background-color: #0d1b2a; color: #e0f0ff; font-family: 'Segoe UI'; font-size: 10pt; }
    QDialog     { background-color: #0d1b2a; }
    QLineEdit   { background-color: #112233; border: 1px solid #1e3a5f; border-radius: 5px; padding: 6px; color: #e0f0ff; }
    QLineEdit:focus { border: 1px solid #2e86de; }
    QComboBox   { background-color: #112233; border: 1px solid #1e3a5f; border-radius: 5px; padding: 6px; color: #e0f0ff; }
    QComboBox QAbstractItemView { background-color: #112233; selection-background-color: #2e86de; }
    QMessageBox { background-color: #0d1b2a; color: #e0f0ff; }
"""

def red_btn():
    return "background-color: #2e86de; color: white; font-weight: bold; padding: 7px 16px; border-radius: 6px;"

def blue_btn():
    return "background-color: #54a0ff; color: white; font-weight: bold; padding: 7px 16px; border-radius: 6px;"

def green_btn():
    return "background-color: #1abc9c; color: white; font-weight: bold; padding: 7px 16px; border-radius: 6px;"

def dark_red_btn():
    return "background-color: #1e3a5f; color: #a8d8ea; font-weight: bold; padding: 7px 16px; border-radius: 6px;"

def gray_btn():
    return "background-color: #2c3e50; color: #aabbcc; padding: 7px 16px; border-radius: 6px;"