package main.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public record PatientIdentity(Name name, Date dateOfBirth)
        implements Comparable<PatientIdentity> {

    public boolean match(PatientIdentity other) {
        return (
                this.name.match(other.name) &&
                        this.dateOfBirth.equals(other.dateOfBirth)
        );
    }

    @Override
    public int compareTo(PatientIdentity other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.dateOfBirth.compareTo(other.dateOfBirth);
    }

    public boolean isLessThan(PatientIdentity other) {
        if (this.name.isLessThan(other.name)) {
            return true;
        } else if (this.name.match(other.name)) {
            return this.dateOfBirth.compareTo(other.dateOfBirth) < 0;
        } else {
            return false;
        }
    }

    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return name.lastName + ", " + name().firstName + ", " + sdf.format(dateOfBirth);
    }

    public static PatientIdentity fromCSV(String csv) {
        String[] parts = csv.split(", ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid format: expected 'Last, First, dd-MM-yyyy'");
        }
        String lastName = parts[0].trim();
        String firstName = parts[1].trim();
        String dobString = parts[2].trim();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dob;
        try {
            dob = sdf.parse(dobString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: expected dd-MM-yyyy", e);
        }

        return new PatientIdentity(new Name(firstName, lastName), dob);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "name: " + name.toString() + " dob: " + sdf.format(dateOfBirth);
    }
}