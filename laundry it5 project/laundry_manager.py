# handles all data operations (add, update, delete, search, report) — the "brain" of the system

import os
from laundry_order import LaundryOrder

data_file = "orders.txt"

class LaundryManager:

    def __init__(self):
        self.records = []
        self.next_id = 1001 
        self.load_orders() 

    # file handling

    def save_orders(self):
        """Save all orders to orders.txt."""
        with open(data_file, "w") as f:
            f.write(f"next_id:{self.next_id}\n")
            for o in self.records:
                f.write(f"{o.order_id},{o.name},{o.service},{o.weight},{o.status}\n")

    def load_orders(self):
        """Load orders from orders.txt if it exists."""
        if not os.path.exists(data_file):
            return 

        try:
            with open(data_file, "r") as f:
                lines = f.readlines()

            if not lines:
                return

            self.next_id = int(lines[0].strip().split(":")[1])

            for line in lines[1:]:
                line = line.strip()
                if line == "":
                    continue
                parts = line.split(",")
                order_id = int(parts[0])
                name     = parts[1]
                service  = parts[2]
                weight   = float(parts[3])
                status   = parts[4]

                order = LaundryOrder(order_id, name, service, weight)
                order.status = status
                self.records.append(order)

        except Exception:
            # corrupted file -> start fresh
            self.records = []
            self.next_id = 1001

    # login

    def login(self, username, password):
        valid_users = {
            "admin": "adminrani123",
            "staff": "staff123"
        }
        return valid_users.get(username) == password

    # crud

    def add_order(self, name, service, weight):
        new_order = LaundryOrder(self.next_id, name, service, weight)
        self.records.append(new_order)
        self.next_id += 1
        self.save_orders() 
        return new_order

    def find_order(self, order_id):
        for order in self.records:
            if order.order_id == order_id:
                return order
        return None

    def update_status(self, order_id, new_status):
        order = self.find_order(order_id)
        if order:
            order.status = new_status
            self.save_orders() 
            return True
        return False

    def delete_order(self, order_id):
        order = self.find_order(order_id)
        if order:
            self.records.remove(order)
            self.save_orders()
            return True
        return False

    # search

    def search_orders(self, keyword):
        results = []
        for order in self.records:
            if keyword.lower() in order.name.lower() or keyword == str(order.order_id):
                results.append(order)
        return results

    # report

    def generate_report(self):
        total     = len(self.records)
        revenue   = sum(o.price for o in self.records)
        pending   = sum(1 for o in self.records if o.status == "Pending")
        washing   = sum(1 for o in self.records if o.status == "Washing")
        drying    = sum(1 for o in self.records if o.status == "Drying")
        completed = sum(1 for o in self.records if o.status == "Completed")

        return {
            "total"    : total,
            "revenue"  : revenue,
            "pending"  : pending,
            "washing"  : washing,
            "drying"   : drying,
            "completed": completed
        }