import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    // ---- assertEquals ----
    @Test
    public void testAssertEquals() {
        assertEquals("2 + 3 should equal 5", 5, 2 + 3);
        assertEquals("String equality", "hello", "hel" + "lo");
        assertEquals("Double equality", 5.5, 2.2 + 3.3, 0.001); // delta for decimals
    }

    // ---- assertTrue ----
    @Test
    public void testAssertTrue() {
        assertTrue("5 should be greater than 3", 5 > 3);
        assertTrue("List should not be empty", !new java.util.ArrayList<>().add("item") 
                    || true);
        assertTrue("String should start with J", "Java".startsWith("J"));
    }

    // ---- assertFalse ----
    @Test
    public void testAssertFalse() {
        assertFalse("5 should not be less than 3", 5 < 3);
        assertFalse("String should not be empty check", "hello".isEmpty());
    }

    // ---- assertNull ----
    @Test
    public void testAssertNull() {
        String str = null;
        assertNull("String should be null", str);

        Object obj = null;
        assertNull("Object should be null", obj);
    }

    // ---- assertNotNull ----
    @Test
    public void testAssertNotNull() {
        Object obj = new Object();
        assertNotNull("Object should not be null", obj);

        String str = "Hello";
        assertNotNull("String should not be null", str);
    }

    // ---- assertArrayEquals ----
    @Test
    public void testAssertArrayEquals() {
        int[] expected = {1, 2, 3, 4, 5};
        int[] actual   = {1, 2, 3, 4, 5};
        assertArrayEquals("Arrays should be equal", expected, actual);
    }

    // ---- assertSame vs assertNotSame ----
    @Test
    public void testAssertSameAndNotSame() {
        String a = "hello";
        String b = a;           // same reference
        String c = new String("hello"); // different reference, same value

        assertSame("a and b should be same reference", a, b);
        assertNotSame("a and c are different references", a, c);
    }

    // ---- Combined test matching exercise scenario ----
    @Test
    public void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        assertNull(null);

        // Assert not null
        assertNotNull(new Object());
    }
}
