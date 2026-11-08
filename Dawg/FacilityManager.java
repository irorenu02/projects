package Dawg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class FacilityManager {
	private final Map<String, Facility> map = new LinkedHashMap<>();

	public void add(Facility f) {
		map.put(f.getId(), f);
	}

	public void update(Facility f) {
		map.put(f.getId(), f);
	}

	public void remove(String id) {
		map.remove(id);
	}

	public Facility getById(String id) {
		return map.get(id);
	}

	public Collection<Facility> getAll() {
		return map.values();
	}

	public String getNextId() {
		int max = 0;
		for (String id : map.keySet()) {
			if (id.startsWith("F")) {
				try {
					int num = Integer.parseInt(id.substring(1));
					if (num > max)
						max = num;
				} catch (NumberFormatException ignored) {
				}
			}
		}
		return String.format("F%03d", max + 1);
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
				Facility f = Facility.fromCSV(line);
				if (f != null)
					map.put(f.getId(), f);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveToFile(String path) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (Facility f : map.values())
				bw.write(f.toCSV() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
