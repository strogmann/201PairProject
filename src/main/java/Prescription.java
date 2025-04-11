package main.java;

import java.time.LocalDate;

public class Prescription {
    private String medicineName;
    private LocalDate dateOfIssue;
    private String dosage;
    private String prescriber;

    public Prescription(String medicineName, LocalDate dateOfIssue, String dosage, String prescriber) {
        this.medicineName = medicineName;
        this.dateOfIssue = dateOfIssue;
        this.dosage = dosage;
        this.prescriber = prescriber;
    }

    public String getMedicineName() { return medicineName; }
    public LocalDate getDateOfIssue() { return dateOfIssue; }
    public String getDosage() { return dosage; }
    public String getPrescriber() { return prescriber; }

    public static Prescription fromCSVLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 6) throw new IllegalArgumentException("Invalid CSV line: " + line);
        return new Prescription(
                parts[2].trim(),
                LocalDate.parse(parts[3].trim()),
                parts[4].trim(),
                parts[5].trim()
        );
    }
}
