package Neyan;

public class entryProperties {
	private String ID, firstName, lastName, gender, purpose;
	private int age;

	public String getID() {
		return ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public entryProperties(String ID, String firstName, String lastName, int age, String gender, String purpose) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.purpose = purpose;
	}

	public String toCSV() {
		return (ID == null ? "" : ID) + "," + (firstName == null ? "" : firstName) + ","
				+ (lastName == null ? "" : lastName) + "," + age + "," + (gender == null ? "" : gender) + ","
				+ (purpose == null ? "" : purpose) + "\n";
	}

	public static entryProperties fromCSV(String line) {
		String[] ele = line.split(",");
		if (ele.length < 6)
			return null;
		return new entryProperties(ele[0], ele[1], ele[2], Integer.parseInt(ele[3]), ele[4], ele[5]);
	}

}
