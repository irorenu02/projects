records = []

def login():
    attempts = 0
    while attempts < 3:
        username = input("Username: ")
        password = input("Password: ")
        if username == "admin" and password == "adminrani123":
            print("Login successful")
            return True
        else:
            attempts += 1
            print(f"Invalid credentials. {3 - attempts} attempt(s) left.")
    print("Access denied.")
    return False

def add_record():
    order_id = len(records) + 1001
    name = input("Customer Name: ")
    service = input("Service (Wash / Dry / Fold): ")
    weight = float(input("Weight (kg): "))
    price = weight * 50
    record = {
        "id": order_id,
        "name": name,
        "service": service,
        "weight": weight,
        "price": price,
        "status": "Pending"
    }
    records.append(record)
    print(f"Order #{order_id} added successfully")

def view_records():
    if not records:
        print("No records found.")
        return
    print(f"\n{'ID':<6} {'Name':<20} {'Service':<12} {'Weight':>7} {'Price':>8}  {'Status':<12}")
    print("-" * 68)
    for r in records:
        print(f"{r['id']:<6} {r['name']:<20} {r['service']:<12} {r['weight']:>6.1f}  {r['price']:>7.2f}  {r['status']:<12}")

def update_record():
    order_id = int(input("Enter Order ID to update: "))
    for record in records:
        if record["id"] == order_id:
            print("Statuses: Pending | Washing | Drying | Completed")
            record["status"] = input("New Status: ")
            print("Record updated successfully.")
            return
    print("Order not found.")

def delete_record():
    order_id = int(input("Enter Order ID to delete: "))
    for record in records:
        if record["id"] == order_id:
            confirm = input(f"Delete order #{order_id} for {record['name']}? (yes/no): ")
            if confirm.lower() == "yes":
                records.remove(record)
                print("Record deleted.")
            else:
                print("Deletion cancelled.")
            return
    print("Order not found.")

def search_record():
    keyword = input("Enter Customer Name or Order ID: ").lower()
    results = []
    for r in records:
        if keyword in r["name"].lower() or keyword == str(r["id"]):
            results.append(r)
    if results:
        print(f"\n{'ID':<6} {'Name':<20} {'Service':<12} {'Weight':>7} {'Price':>8}  {'Status':<12}")
        print("-" * 68)
        for r in results:
            print(f"{r['id']:<6} {r['name']:<20} {r['service']:<12} {r['weight']:>6.1f}  {r['price']:>7.2f}  {r['status']:<12}")
    else:
        print("No matching records found.")

def generate_report():
    total = len(records)
    revenue = sum(r["price"] for r in records)
    completed = sum(1 for r in records if r["status"] == "Completed")
    pending = sum(1 for r in records if r["status"] == "Pending")
    washing = sum(1 for r in records if r["status"] == "Washing")
    drying = sum(1 for r in records if r["status"] == "Drying")
    print("\n--- LAUNDRY SHOP REPORT ---")
    print(f"Total Orders  : {total}")
    print(f"Completed     : {completed}")
    print(f"Pending       : {pending}")
    print(f"Washing       : {washing}")
    print(f"Drying        : {drying}")
    print(f"Total Revenue : PHP {revenue:.2f}")

print("--- LAUNDRY MONITORING AND SERVICES SYSTEM ---")

if login():
    while True:
        print("\n--- MAIN MENU ---")
        print("1. Add Record")
        print("2. View Records")
        print("3. Update Record")
        print("4. Delete Record")
        print("5. Search Data")
        print("6. Generate Report")
        print("7. Exit")
        choice = input("Choose option: ")

        if choice == "1":
            add_record()
        elif choice == "2":
            view_records()
        elif choice == "3":
            update_record()
        elif choice == "4":
            delete_record()
        elif choice == "5":
            search_record()
        elif choice == "6":
            generate_report()
        elif choice == "7":
            print("Thank you for using the Laundry Monitoring System. Kbye")
            break
        else:
            print("Invalid option. Please enter a number from 1 to 7.")