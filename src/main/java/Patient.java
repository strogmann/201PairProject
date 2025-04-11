
package main.java;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Patient {

    private PatientIdentity patientIdentity = null;

    public Patient(PatientIdentity identity) {
        patientIdentity=identity;
    }

    @Override
    public String toString() {
        return "identity: " + patientIdentity.toString();
    }

    public String toCSV() {
        return patientIdentity.toCSV();
    }

    public static Patient fromCSV(String csv) {
        PatientIdentity identity = PatientIdentity.fromCSV(csv);
        return new Patient(identity);
    }

    public static Patient makePatient(String line) {
        try {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter(",");

            String lastName = scanner.next().trim();
            String firstName = scanner.next().trim();
            String dobStr = scanner.next().trim();

            Name name = new Name(firstName, lastName);
            Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(dobStr);

            return new Patient(new PatientIdentity(name, dob));
        } catch (Exception e) {
            return null;
        }
    }
}
