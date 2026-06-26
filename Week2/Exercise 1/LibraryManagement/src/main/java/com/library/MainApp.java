package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * MainApp — loads the Spring ApplicationContext
 * and tests the configuration.
 *
 * Key point: MainApp never calls 'new BookService()'
 * or 'new BookRepository()' — Spring creates and
 * wires everything based on applicationContext.xml
 */
public class MainApp {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   Library Management System           ");
        System.out.println("   Powered by Spring Core              ");
        System.out.println("========================================\n");

        System.out.println("--- Loading Spring ApplicationContext ---");

        // ClassPathXmlApplicationContext reads applicationContext.xml
        // from src/main/resources/ and initializes all beans
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "applicationContext.xml");

        System.out.println("--- Spring Context Loaded Successfully ---\n");

        // getBean("bookService") — retrieves the fully configured
        // BookService bean from the Spring container
        // Spring has already injected BookRepository into it
        BookService bookService =
                context.getBean("bookService", BookService.class);

        // ---- Test 1: Display all books ----
        bookService.displayAllBooks();

        // ---- Test 2: Add a new book ----
        bookService.addBook("Design Patterns — GoF");

        // ---- Test 3: Display again to see the addition ----
        bookService.displayAllBooks();

        // ---- Test 4: Search for a book ----
        bookService.searchBook("Clean Code");
        bookService.searchBook("Harry Potter");

        // ---- Test 5: Remove a book ----
        bookService.removeBook("The Great Gatsby");

        // ---- Test 6: Display final state ----
        bookService.displayAllBooks();

        // ---- Test 7: Prove Singleton behavior ----
        System.out.println("\n--- Singleton Bean Proof ---");
        BookService bookService2 =
                context.getBean("bookService", BookService.class);
        System.out.println("bookService == bookService2 ? "
                + (bookService == bookService2));
        System.out.println("(Both are the SAME Spring-managed instance)");

        // Always close the context — triggers bean destruction
        ((ClassPathXmlApplicationContext) context).close();

        System.out.println("\n========================================");
        System.out.println("   Application Finished                ");
        System.out.println("========================================");
    }
}
