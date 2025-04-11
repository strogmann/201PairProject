package test.main.java;

import main.java.Prescription;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionTest {

    @Test
    void getMedicineName() {
        Prescription p = new Prescription("Ibuprofen", LocalDate.of(2024, 4, 10), "200mg", "Dr. Green");
        assertEquals("Ibuprofen", p.getMedicineName());
    }

    @Test
    void getDateOfIssue() {
        Prescription p = new Prescription("Ibuprofen", LocalDate.of(2024, 4, 10), "200mg", "Dr. Green");
        assertEquals(LocalDate.of(2024, 4, 10), p.getDateOfIssue());
    }

    @Test
    void getDosage() {
        Prescription p = new Prescription("Ibuprofen", LocalDate.of(2024, 4, 10), "200mg", "Dr. Green");
        assertEquals("200mg", p.getDosage());
    }

    @Test
    void getPrescriber() {
        Prescription p = new Prescription("Ibuprofen", LocalDate.of(2024, 4, 10), "200mg", "Dr. Green");
        assertEquals("Dr. Green", p.getPrescriber());
    }

    @Test
    void fromCSVLine() {
        String csv = "Jane Doe,1985-03-22,Amoxicillin,2024-04-05,500mg,Dr. Brown";
        Prescription p = Prescription.fromCSVLine(csv);
        assertEquals("Amoxicillin", p.getMedicineName());
        assertEquals(LocalDate.of(2024, 4, 5), p.getDateOfIssue());
        assertEquals("500mg", p.getDosage());
        assertEquals("Dr. Brown", p.getPrescriber());
    }
}