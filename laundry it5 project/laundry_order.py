# stores the details of one customer

class LaundryOrder:

    def __init__(self, order_id, name, service, weight):
        self.order_id = order_id
        self.name     = name
        self.service  = service
        self.weight   = weight
        self.price    = weight * 50   # PHP 50 per kg
        self.status   = "Pending"     # every new order starts as Pending
