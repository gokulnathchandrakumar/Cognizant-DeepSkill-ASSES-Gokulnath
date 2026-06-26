package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * LibraryManagementApplication
 * Loads Spring context and tests all DI configurations
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Library Management System          ║");
        System.out.println("║   Spring DI Demonstration            ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // ---- Load Spring Context ----
        System.out.println(">>> Loading Spring ApplicationContext...\n");
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "applicationContext.xml");
        System.out.println("\n>>> Spring Context Loaded!\n");

        // ============================================================
        // TEST 1: Setter Injection bean
        // ============================================================
        System.out.println("════════════════════════════════════════");
        System.out.println("TEST 1: Setter Injection");
        System.out.println("════════════════════════════════════════");

        BookService bookService =
                context.getBean("bookService", BookService.class);

        // Verify injection worked
        System.out.println("Service Name: "
                + bookService.getServiceName());
        System.out.println("Repository Type: "
                + bookService.getBookRepository().getRepositoryType());

        // Display all books
        bookService.displayAllBooks();

        // Add a new book
        bookService.addBook(6, "Head First Java",
                "Kathy Sierra", "978-0596009205");

        // Search operations
        bookService.searchBookById(3);
        bookService.searchBookByTitle("Clean Code");
        bookService.searchBookByTitle("Harry Potter");

        // Borrow and return
        bookService.borrowBook(1);
        bookService.borrowBook(1); // try borrowing again
        bookService.returnBook(1);

        // Delete
        bookService.deleteBook(4);

        // Display final state
        bookService.displayAllBooks();

        // ============================================================
        // TEST 2: Constructor Injection bean
        // ============================================================
        System.out.println("\n════════════════════════════════════════");
        System.out.println("TEST 2: Constructor Injection");
        System.out.println("════════════════════════════════════════");

        BookService bookServiceConstructor =
                context.getBean("bookServiceConstructor",
                        BookService.class);

        System.out.println("Service Name: "
                + bookServiceConstructor.getServiceName());
        System.out.println("Repository Type: "
                + bookServiceConstructor.getBookRepository()
                                        .getRepositoryType());
        bookServiceConstructor.displayAllBooks();

        // ============================================================
        // TEST 3: Singleton Proof
        // ============================================================
        System.out.println("\n════════════════════════════════════════");
        System.out.println("TEST 3: Singleton Bean Proof");
        System.out.println("════════════════════════════════════════");

        BookService bookService2 =
                context.getBean("bookService", BookService.class);

        System.out.println("bookService == bookService2 ? "
                + (bookService == bookService2));
        System.out.println("Same instance — Spring returns the");
        System.out.println("SAME bean every time for Singleton scope.");

        // ============================================================
        // TEST 4: Shared Repository Proof
        // ============================================================
        System.out.println("\n════════════════════════════════════════");
        System.out.println("TEST 4: Shared Repository Proof");
        System.out.println("════════════════════════════════════════");

        System.out.println("bookService repository == "
                + "bookServiceConstructor repository? "
                + (bookService.getBookRepository()
                   == bookServiceConstructor.getBookRepository()));
        System.out.println("Both BookService beans share the SAME");
        System.out.println("BookRepository Singleton instance!");

        // Close context
        ((ClassPathXmlApplicationContext) context).close();

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   Application Complete               ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}
