package Dawg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookingManager {
	private final Map<String, Booking> map = new LinkedHashMap<>();

	public void add(Booking b) {
		map.put(b.getId(), b);
	}

	public void update(Booking b) {
		map.put(b.getId(), b);
	}

	public void remove(String id) {
		map.remove(id);
	}

	public Booking getById(String id) {
		return map.get(id);
	}

	public List<Booking> getAll() {
		return new ArrayList<>(map.values());
	}

	public void removeByFacilityId(String facilityId) {
		map.values().removeIf(b -> b.getFacilityId().equals(facilityId));
	}

	public String getNextId() {
		int max = 0;
		for (String id : map.keySet()) {
			if (id.startsWith("S")) {
				try {
					int num = Integer.parseInt(id.substring(1));
					if (num > max)
						max = num;
				} catch (NumberFormatException ignored) {
				}
			}
		}
		return String.format("S%03d", max + 1);
	}

	public String checkConflict(Booking newB) {
		for (Booking b : map.values()) {
			if (!b.getFacilityId().equals(newB.getFacilityId()))
				continue;
			if (!b.getDate().equals(newB.getDate()))
				continue;
			boolean overlap = newB.getStart().isBefore(b.getEnd()) && newB.getEnd().isAfter(b.getStart());
			if (overlap && !b.getId().equals(newB.getId())) {
				return String.format("Conflict with %s %s-%s (Booked by: %s)", b.getDate(),
						b.getStart().format(DateTimeFormatter.ofPattern("HH:mm")),
						b.getEnd().format(DateTimeFormatter.ofPattern("HH:mm")), b.getBookedBy());
			}
		}
		return null;
	}

	public void loadFromFile(String path) {
		map.clear();
		File file = new File(path);
		if (!file.exists())
			return;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.isBlank())
					continue;
				Booking b = Booking.fromCSV(line);
				if (b != null)
					map.put(b.getId(), b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveToFile(String path) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (Booking b : map.values())
				bw.write(b.toCSV() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
