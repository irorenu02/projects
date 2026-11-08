package Dawg;

public class Facility {
	private String id, facility, location;
	private int capacity;
	private boolean available;

	public String getId() {
		return id;
	}

	public String getFacility() {
		return facility;
	}

	public String getLocation() {
		return location;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setName(String name) {
		this.facility = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Facility(String id, String name, String location, int capacity, boolean available) {
		this.id = id;
		this.facility = name;
		this.location = location;
		this.capacity = capacity;
		this.available = available;
	}

	public String toCSV() {
		return (id == null ? "" : id) + "," + (facility == null ? "" : facility) + ","
				+ (location == null ? "" : location) + "," + capacity + "," + (available ? "1" : "0");
	}

	public static Facility fromCSV(String line) {
		String[] parts = line.split(",");
		if (parts.length < 5)
			return null;
		return new Facility(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), parts[4].equals("1"));
	}
}
