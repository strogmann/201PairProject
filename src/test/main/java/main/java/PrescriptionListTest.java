import main.java.Prescription;
import main.java.PrescriptionList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionListTest {

    @Test
    void add() {
        PrescriptionList list = new PrescriptionList();
        Prescription p1 = new Prescription("Amoxicillin", LocalDate.of(2024, 4, 1), "500mg", "Dr. Smith");
        Prescription p2 = new Prescription("Ibuprofen", LocalDate.of(2024, 4, 5), "200mg", "Dr. Doe");

        list.add(p1);
        list.add(p2);

        list.init();
        Prescription first = list.next();
        Prescription second = list.next();

        assertEquals(p2, first);
        assertEquals(p1, second);
        assertNull(list.next());
    }

    @Test
    void init() {
        PrescriptionList list = new PrescriptionList();
        list.init();
        assertNull(list.next());
    }

    @Test
    void next() {
        PrescriptionList list = new PrescriptionList();
        Prescription p = new Prescription("TestMed", LocalDate.of(2024, 4, 10), "100mg", "Dr. Test");
        list.add(p);
        list.init();
        assertEquals(p, list.next());
        assertNull(list.next());
    }
}