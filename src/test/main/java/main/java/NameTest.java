import main.java.Name;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void fullName() {
        Name name = new Name("John", "Doe");
        assertEquals("Doe, John", name.fullName());
    }

    @Test
    void match() {
        Name name1 = new Name("John", "Doe");
        Name name2 = new Name("john", "doe");
        assertTrue(name1.match(name2));
    }

    @Test
    void isLessThan() {
        Name name1 = new Name("Alice", "Smith");
        Name name2 = new Name("Bob", "Smith");
        assertTrue(name1.isLessThan(name2));
    }

    @Test
    void compareTo() {
        Name name1 = new Name("Alice", "Smith");
        Name name2 = new Name("Bob", "Smith");
        assertTrue(name1.compareTo(name2) < 0);
    }

    @Test
    void testToString() {
        Name name = new Name("Jane", "Doe");
        assertEquals("Doe, Jane", name.toString());
    }

    @Test
    void fromString() {
        Name name = Name.fromString("Jane Doe");
        assertEquals("Doe, Jane", name.toString());
    }
}
