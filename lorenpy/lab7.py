# Base Class (Abstraction)
class LibraryItem:
    def __init__(self, title, author):
        self.title = title
        self.author = author
        self.__status = "Available"  # Encapsulation - private attribute

    # Encapsulation - getter and setter
    def get_status(self):
        return self.__status

    def set_status(self, status):
        self.__status = status

    # base method
    def display_info(self):
        print(f"Item: {self.title} by {self.author}")
        print(f"Status: {self.get_status()}")


# Inheritance - Book inherits from the LibraryItem
class Book(LibraryItem):
    def __init__(self, title, author, year_published):
        super().__init__(title, author)
        self.year_published = year_published

    # method overiding
    def display_info(self):
        print(f"Book: {self.title} by {self.author} ({self.year_published})")
        print(f"Status: {self.get_status()}")


# Polymorphism - Magazine inherits from LibraryItem
class Magazine(LibraryItem):
    def __init__(self, title, author, issue):
        super().__init__(title, author)
        self.issue = issue

    # own version of display_info
    def display_info(self):
        print(f"Magazine: {self.title} by {self.author}")
        print(f"Issue: {self.issue}")
        print(f"Status: {self.get_status()}")


# Main Program
book1 = Book("Python Basics", "John Cena", 2016)
mag1 = Magazine("Tech Today", "Will Smith", "March 2026")

book1.display_info()
print()

mag1.set_status("Checked Out")
mag1.display_info()