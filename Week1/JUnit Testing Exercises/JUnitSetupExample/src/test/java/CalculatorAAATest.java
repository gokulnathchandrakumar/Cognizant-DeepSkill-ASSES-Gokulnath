import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class CalculatorAAATest {

    // Test fixture — shared object across all tests
    private Calculator calculator;

    @BeforeClass
    public static void setUpClass() {
        // Runs ONCE before all tests in this class
        System.out.println("=== Test Suite Starting ===");
    }

    @AfterClass
    public static void tearDownClass() {
        // Runs ONCE after all tests in this class
        System.out.println("=== Test Suite Complete ===");
    }

    @Before
    public void setUp() {
        // Runs before EVERY test — fresh Calculator each time
        calculator = new Calculator();
        System.out.println("-- setUp: new Calculator created --");
    }

    @After
    public void tearDown() {
        // Runs after EVERY test — cleanup
        calculator = null;
        System.out.println("-- tearDown: Calculator cleared --");
    }

    // ---- AAA Pattern: Addition ----
    @Test
    public void testAdd() {
        // Arrange
        int numberA = 10;
        int numberB = 5;
        int expectedResult = 15;

        // Act
        int actualResult = calculator.add(numberA, numberB);

        // Assert
        assertEquals("10 + 5 should equal 15", expectedResult, actualResult);
    }

    // ---- AAA Pattern: Subtraction ----
    @Test
    public void testSubtract() {
        // Arrange
        int numberA = 10;
        int numberB = 4;
        int expectedResult = 6;

        // Act
        int actualResult = calculator.subtract(numberA, numberB);

        // Assert
        assertEquals("10 - 4 should equal 6", expectedResult, actualResult);
    }

    // ---- AAA Pattern: Multiplication ----
    @Test
    public void testMultiply() {
        // Arrange
        int numberA = 6;
        int numberB = 7;
        int expectedResult = 42;

        // Act
        int actualResult = calculator.multiply(numberA, numberB);

        // Assert
        assertEquals("6 * 7 should equal 42", expectedResult, actualResult);
    }

    // ---- AAA Pattern: Division ----
    @Test
    public void testDivide() {
        // Arrange
        int numberA = 10;
        int numberB = 2;
        double expectedResult = 5.0;

        // Act
        double actualResult = calculator.divide(numberA, numberB);

        // Assert
        assertEquals("10 / 2 should equal 5.0", expectedResult, actualResult, 0.001);
    }

    // ---- AAA Pattern: Division by zero ----
    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        // Arrange
        int numberA = 10;
        int numberB = 0;

        // Act — expects ArithmeticException to be thrown
        calculator.divide(numberA, numberB);

        // Assert — handled by @Test(expected = ...) annotation
    }

    // ---- Negative numbers ----
    @Test
    public void testAddNegativeNumbers() {
        // Arrange
        int numberA = -5;
        int numberB = -3;
        int expectedResult = -8;

        // Act
        int actualResult = calculator.add(numberA, numberB);

        // Assert
        assertEquals("-5 + -3 should equal -8", expectedResult, actualResult);
    }

    // ---- @Ignore example ----
    @Ignore("Feature not yet implemented")
    @Test
    public void testSquareRoot() {
        // This test is skipped — shown as SKIPPED in results
        // Equivalent of @Disabled in JUnit 5
    }
}
