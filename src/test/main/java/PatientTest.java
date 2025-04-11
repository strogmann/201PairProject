
package test.main.java;

import main.java.Name;
import main.java.Patient;
import main.java.PatientIdentity;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient createSamplePatient() throws Exception {
        Name name = new Name("Alice", "Smith");
        Date dob = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1990");
        PatientIdentity identity = new PatientIdentity(name, dob);
        return new Patient(identity);
    }

    @Test
    void testToString() throws Exception {
        Patient patient = createSamplePatient();
        assertTrue(patient.toString().contains("Alice"));
        assertTrue(patient.toString().contains("Smith"));
        assertTrue(patient.toString().contains("01-01-1990"));
    }

    @Test
    void toCSV() throws Exception {
        Patient patient = createSamplePatient();
        String csv = patient.toCSV();
        assertEquals("Smith, Alice, 01-01-1990", csv);
    }

    @Test
    void fromCSV() {
        String csv = "Smith, Alice, 01-01-1990";
        Patient patient = Patient.fromCSV(csv);
        assertEquals("Smith, Alice", patient.patientIdentity().name().toString());
    }

    @Test
    void patientIdentity() throws Exception {
        Patient patient = createSamplePatient();
        assertNotNull(patient.patientIdentity());
        assertEquals("Smith, Alice", patient.patientIdentity().name().toString());
    }
}
