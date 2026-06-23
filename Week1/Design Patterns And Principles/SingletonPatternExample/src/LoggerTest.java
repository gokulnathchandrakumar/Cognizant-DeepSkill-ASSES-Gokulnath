public class LoggerTest {
    public static void main(String[] args) {
        System.out.println("---- Test 1: Same instance check ----");
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("First message from logger1");
        logger2.log("Second message from logger2");

        System.out.println("logger1 count: " + logger1.getLogCount());
        System.out.println("logger2 count: " + logger2.getLogCount());

        System.out.println("Are logger1 and logger2 the same instance? "
                + (logger1 == logger2));

        System.out.println("\n---- Test 2: Multi-threaded check ----");
        
        Runnable task = () -> {
            Logger logger = Logger.getInstance();
            logger.log(Thread.currentThread().getName() + " is logging");
        };
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
