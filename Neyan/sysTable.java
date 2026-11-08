package Neyan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class sysTable {
	private final Map<String,entryProperties> map = new LinkedHashMap<>();
	
	public void addUpdate (entryProperties e) {
		map.put(e.getID(),e);
	}
	
	public void remove(String ID) {
		map.remove(ID);
	}
	
	public entryProperties getByID(String ID) {
		return map.get(ID);
	}
	
	public Collection<entryProperties> getAll(){
		return map.values();
	}
	
	public void clearAll() {
		map.clear();
	}
	
	public String getNextID() {
		int max = 0 ;
		for (String id : map.keySet()) {
			if (id.startsWith("F")) {
				try {
					int num = Integer.parseInt(id.substring(1));
					if(num > max) {
						max = num;
					}
				}catch(NumberFormatException ignored) {}
			}
		}
		return String.format("F%03d", max + 1);
	}
	
	public void loadFile(String path) {
		map.clear();
		File f = new File(path);
		if(!f.exists()) return;
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			String line;
			while((line = br.readLine()) != null) {
				if(line.isBlank()) continue;
				entryProperties e = entryProperties.fromCSV(line);
				if(e != null) map.put(e.getID(), e);
			}
		}catch (Exception e) {}
	
	}
	
	public void saveFile(String path) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			for(entryProperties e : map.values()) 
				bw.write(e.toCSV());
				bw.newLine();
		}catch(IOException e) {}
	}
}
