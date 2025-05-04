import main.java.Name;
import main.java.PatientIdentity;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PatientIdentityTest {

    @Test
    void testMatch() throws Exception {
        Name name1 = new Name("Jane", "Doe");
        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");

        PatientIdentity id1 = new PatientIdentity(name1, dob);
        PatientIdentity id2 = new PatientIdentity(new Name("jane", "doe"), dob);

        assertTrue(id1.match(id2));
    }

    @Test
    void testCompareToAndIsLessThan() throws Exception {
        Date dob1 = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");
        Date dob2 = new SimpleDateFormat("yyyy-MM-dd").parse("1985-01-01");

        PatientIdentity a = new PatientIdentity(new Name("Alice", "Smith"), dob1);
        PatientIdentity b = new PatientIdentity(new Name("Bob", "Smith"), dob2);

        assertTrue(a.compareTo(b) < 0);
        assertTrue(a.isLessThan(b));
    }

    @Test
    void testToCSVAndFromCSV() throws Exception {
        Name name = new Name("Jane", "Doe");
        Date dob = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1990");
        PatientIdentity id = new PatientIdentity(name, dob);

        String csv = id.toCSV();
        assertEquals("Doe, Jane, 01-01-1990", csv);

        PatientIdentity parsed = PatientIdentity.fromCSV(csv);
        assertTrue(parsed.match(id));
    }

    @Test
    void testToString() throws Exception {
        Name name = new Name("Jane", "Doe");
        Date dob = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1990");
        PatientIdentity id = new PatientIdentity(name, dob);

        String str = id.toString();
        assertTrue(str.contains("Jane"));
        assertTrue(str.contains("Doe"));
        assertTrue(str.contains("01-01-1990"));
    }
}
