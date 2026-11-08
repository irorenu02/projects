package Neyan;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderManager {
    private final String filePath;

    /**
     * @param filePath path to append customers data, e.g. "customers.txt"
     */
    public OrderManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves a combined CSV line:
     * First,Middle,Last,BirthYear,Gender,Quantity,TotalPrice
     *
     * Appends to file specified in constructor.
     */
    public void save(Customer customer, Order order) throws IOException {
        String line = customer.toCSVPrefix() + "," + order.toCSVSuffix();
        // Use PrintWriter for convenience and true append
        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(line);
        }
    }
}
