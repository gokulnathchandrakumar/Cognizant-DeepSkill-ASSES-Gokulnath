import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {

    // One logger per class — standard practice
    // LoggerFactory.getLogger(LoggingExample.class) ties the logger
    // to this specific class so log output shows the exact class name
    private static final Logger logger = 
            LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {

        System.out.println("=== Application Starting ===\n");

        // ---- Basic levels as per exercise ----
        logger.error("This is an error message");
        logger.warn("This is a warning message");

        // ---- Full demonstration of all levels ----
        System.out.println("\n=== All Log Levels Demo ===\n");

        logger.trace("TRACE: Entering main method — most detailed level");
        logger.debug("DEBUG: Application initialized with args count: {}", args.length);
        logger.info("INFO:  Application started successfully");
        logger.warn("WARN:  Configuration file not found, using defaults");
        logger.error("ERROR: Database connection failed — retrying...");

        // ---- Parameterized logging — preferred over string concatenation ----
        System.out.println("\n=== Parameterized Logging Demo ===\n");

        String username = "john_doe";
        int userId = 1042;
        double balance = 15000.75;

        // RIGHT way — use {} placeholders, not string concatenation
        // The string is only built if this log level is active — saves memory
        logger.info("User logged in: {} (ID: {})", username, userId);
        logger.debug("Account balance for user {}: ${}", username, balance);
        logger.warn("Balance below threshold for user {} — balance: ${}", 
                    username, balance);

        // WRONG way (avoid this) — string is always built even if DEBUG is off
        // logger.debug("User: " + username + " balance: " + balance); // BAD

        // ---- Logging exceptions — the most important real-world usage ----
        System.out.println("\n=== Exception Logging Demo ===\n");

        try {
            int result = divide(10, 0);
        } catch (ArithmeticException e) {
            // Always pass the exception as the LAST argument — Logback prints the
            // full stack trace automatically
            logger.error("Division operation failed for inputs 10 and 0: {}", 
                         e.getMessage(), e);
        }

        try {
            String str = null;
            str.length(); // NullPointerException
        } catch (NullPointerException e) {
            logger.error("Null reference encountered in main method", e);
        }

        // ---- Conditional logging — checking level before expensive operations ----
        System.out.println("\n=== Conditional Logging Demo ===\n");

        if (logger.isDebugEnabled()) {
            // Only build this expensive string if DEBUG level is actually active
            logger.debug("Detailed system state: {}", getExpensiveDebugInfo());
        }

        logger.info("Application execution complete.");
        System.out.println("\n=== Application Finished ===");
    }

    private static int divide(int a, int b) {
        logger.debug("Dividing {} by {}", a, b);
        return a / b; // throws ArithmeticException if b == 0
    }

    private static String getExpensiveDebugInfo() {
        // Simulates an expensive operation only called when DEBUG is enabled
        return "Memory: " + Runtime.getRuntime().freeMemory() + 
               " bytes free | Threads: " + Thread.activeCount();
    }
}
