
package test.main.java;

import main.java.Name;
import main.java.Patient;
import main.java.PatientIdentity;
import main.java.PatientList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class PatientListTest {

    private PatientList list;
    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    void setup() throws Exception {
        list = new PatientList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        patient1 = new Patient(new PatientIdentity(new Name("Alice", "Smith"), sdf.parse("1990-01-01")));
        patient2 = new Patient(new PatientIdentity(new Name("Bob", "Johnson"), sdf.parse("1985-05-15")));

        list.add(patient1);
        list.add(patient2);
    }

    @Test
    void testAddAndFind() {
        Patient found = list.find(patient1.patientIdentity());
        assertNotNull(found);
        assertEquals(patient1.toString(), found.toString());
    }

    @Test
    void testSortAndIteration() {
        list.initIteration();
        Patient first = list.next();
        assertNotNull(first);
        assertNotNull(list.next()); // second
        assertNull(list.next()); // should be done iterating
    }

    @Test
    void testSaveToFileAndImportFromFile() {
        String filename = "test_output.csv";
        assertTrue(list.saveToFile(filename));

        PatientList importedList = new PatientList();
        assertTrue(importedList.importFromFile(filename));

        Patient found = importedList.find(patient2.patientIdentity());
        assertNotNull(found);
        assertEquals(patient2.toString(), found.toString());

        //noinspection ResultOfMethodCallIgnored
        new File(filename).delete(); // clean up
    }
}
