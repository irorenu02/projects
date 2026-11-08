package Neyan;

public class Order {
    private int quantity;
    private boolean withDrinks;
    private static final int PRICE_WITH_DRINKS = 60;
    private static final int PRICE_WITHOUT_DRINKS = 40;

    public Order(int quantity, boolean withDrinks) {
        this.quantity = quantity;
        this.withDrinks = withDrinks;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isWithDrinks() {
        return withDrinks;
    }

    public int getUnitPrice() {
        return withDrinks ? PRICE_WITH_DRINKS : PRICE_WITHOUT_DRINKS;
    }

    public int getTotalPrice() {
        return getUnitPrice() * quantity;
    }

    public String getOrderType() {
        return withDrinks ? "Burger with Drinks" : "Burger";
    }

    /**
     * Returns the CSV suffix for order portion:
     * Quantity,TotalPrice
     */
    public String toCSVSuffix() {
        return String.format("%d,%d", quantity, getTotalPrice());
    }
}
