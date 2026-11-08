package Examination;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private static int counter = 1;

    private int id;
    private String facility;
    private String user;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    public Booking(String facility, String user, LocalDate date, LocalTime start, LocalTime end) {
        this.id = counter++;
        this.facility = facility;
        this.user = user;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public int getId() { return id; }
    public String getFacility() { return facility; }
    public String getUser() { return user; }
    public LocalDate getDate() { return date; }
    public LocalTime getStart() { return start; }
    public LocalTime getEnd() { return end; }

    @Override
    public String toString() {
        return id + "," + facility + "," + user + "," + date + "," + start + "," + end;
    }

    public static Booking fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) return null;

        Booking b = new Booking(
                parts[1],
                parts[2],
                LocalDate.parse(parts[3]),
                LocalTime.parse(parts[4]),
                LocalTime.parse(parts[5])
        );
        b.id = Integer.parseInt(parts[0]);
        if (b.id >= counter) counter = b.id + 1;
        return b;
    }
}
