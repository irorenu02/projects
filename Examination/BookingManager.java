package Examination;

import java.io.*;
import java.util.*;

public class BookingManager {
    public static final String DEFAULT_FILE = "/Users/makima/Desktop/untitled folder/School/src/Examination/booking.txt";
    private List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(int id) {
        bookings.removeIf(b -> b.getId() == id);
    }

    public List<Booking> getAllBookings() {
        bookings.sort(Comparator.comparing(Booking::getDate).thenComparing(Booking::getStart));
        return bookings;
    }

    public void saveToFile(String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (Booking b : bookings) {
                out.println(b.toString());
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        bookings.clear();
        File f = new File(filename);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                Booking b = Booking.fromString(line);
                if (b != null) bookings.add(b);
            }
        }
    }
}
