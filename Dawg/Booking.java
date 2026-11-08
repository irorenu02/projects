package Dawg;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
	private String id, facilityId, bookedBy;
	private LocalDate date;
	private LocalTime start, end;

	public String getId() {
		return id;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public String getBookedBy() {
		return bookedBy;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getStart() {
		return start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

	public Booking(String id, String facilityId, LocalDate date, LocalTime start, LocalTime end, String bookedBy) {
		this.id = id;
		this.facilityId = facilityId;
		this.date = date;
		this.start = start;
		this.end = end;
		this.bookedBy = bookedBy;
	}

	public String toCSV() {
		return (id == null ? "" : id) + "," + (facilityId == null ? "" : facilityId) + ","
				+ (date == null ? "" : date.toString()) + "," + (start == null ? "" : start.toString()) + ","
				+ (end == null ? "" : end.toString()) + "," + (bookedBy == null ? "" : bookedBy);
	}

	public static Booking fromCSV(String line) {
		String[] p = line.split(",");
		if (p.length < 6)
			return null;
		return new Booking(p[0], p[1], LocalDate.parse(p[2]), LocalTime.parse(p[3]), LocalTime.parse(p[4]), p[5]);
	}
}
