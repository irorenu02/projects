package Neyan;

public class Customer {
    private String firstName;
    private String middleName;
    private String lastName;
    private int birthYear;
    private String gender;

    public Customer(String firstName, String middleName, String lastName, int birthYear, String gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }

    /**
     * Returns formatted name: First M. Last
     * If middle name is empty, it falls back to First Last.
     */
    public String getFormattedName() {
        if (middleName != null && middleName.length() > 0) {
            return firstName + " " + middleName.charAt(0) + ". " + lastName;
        } else {
            return firstName + " " + lastName;
        }
    }

    /**
     * Returns CSV line portion for this customer:
     * First,Middle,Last,BirthYear,Gender
     */
    public String toCSVPrefix() {
        return String.format("%s,%s,%s,%d,%s", firstName, middleName, lastName, birthYear, gender);
    }
}
