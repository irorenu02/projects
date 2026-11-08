class ContactManager:
    def __init__(self, filename="contacts.txt"):
        self.filename = filename

    def add_contact(self, name, phone, email):
        with open(self.filename, "a") as f:
            f.write(f"{name},{phone},{email}\n")
        print(f"\nContact '{name}' added successfully!")

    def view_contacts(self):
        try:
            with open(self.filename, "r") as f:
                lines = f.readlines()

            if not lines:
                print("\nNo contacts found.")
                return

            print("\n===== ALL CONTACTS =====")
            for i, line in enumerate(lines, 1):
                name, phone, email = line.strip().split(",")
                print(f"{i}. Name  : {name}")
                print(f"   Phone : {phone}")
                print(f"   Email : {email}")
                print()

        except FileNotFoundError:
            print("\nNo contacts found.")

    def search_contact(self, search_name):
        try:
            with open(self.filename, "r") as f:
                lines = f.readlines()

            found = False
            for line in lines:
                name, phone, email = line.strip().split(",")
                if search_name.lower() == name.lower():
                    print(f"\n===== CONTACT FOUND =====")
                    print(f"Name  : {name}")
                    print(f"Phone : {phone}")
                    print(f"Email : {email}")
                    found = True
                    break

            if not found:
                print(f"\nContact '{search_name}' not found.")

        except FileNotFoundError:
            print("\nNo contacts found.")

manager = ContactManager()

while True:
    print("\n===== CONTACT LIST SYSTEM =====")
    print("1. Add Contact")
    print("2. View Contacts")
    print("3. Search Contact")
    print("4. Exit")
    choice = input("Enter your choice: ")

    if choice == "1":
        name  = input("Enter name: ")
        phone = input("Enter phone number: ")
        email = input("Enter email address: ")
        manager.add_contact(name, phone, email)

    elif choice == "2":
        manager.view_contacts()

    elif choice == "3":
        name = input("Enter name to search: ")
        manager.search_contact(name)

    elif choice == "4":
        print("\nGoodbye!")
        break

    else:
        print("\nInvalid choice. Please try again.")