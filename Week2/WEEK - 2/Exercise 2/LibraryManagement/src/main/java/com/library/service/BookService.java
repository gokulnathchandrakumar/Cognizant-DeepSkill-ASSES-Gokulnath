package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import java.util.List;
import java.util.Optional;

/**
 * BookService — business logic layer
 *
 * Demonstrates all THREE types of Dependency Injection:
 * 1. Setter Injection   — setBookRepository() called by Spring
 * 2. Constructor Injection — via parameterized constructor
 * 3. Field Injection    — shown conceptually with @Autowired comment
 *
 * For this exercise, SETTER INJECTION is the primary approach
 * as it maps directly to the XML configuration.
 */
public class BookService {

    // The dependency — Spring will inject this
    // BookService does NOT create BookRepository itself
    private BookRepository bookRepository;

    private String serviceName;

    // ============================================================
    // TYPE 1: SETTER INJECTION (primary for this exercise)
    // Spring reads applicationContext.xml, sees:
    //   <property name="bookRepository" ref="bookRepository"/>
    // and calls this method automatically after bean creation
    // ============================================================
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] Setter Injection successful!");
        System.out.println("[BookService] BookRepository injected: "
                + bookRepository.getRepositoryType());
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
        System.out.println("[BookService] Service name set to: "
                + serviceName);
    }

    // ============================================================
    // TYPE 2: CONSTRUCTOR INJECTION
    // Used when dependency is MANDATORY (can't work without it)
    // Promotes immutability — dependency can't be changed after creation
    // In XML: use <constructor-arg ref="bookRepository"/>
    // ============================================================
    public BookService() {
        // Default constructor — used with setter injection
        System.out.println("[BookService] Default constructor called");
    }

    public BookService(BookRepository bookRepository, String serviceName) {
        // Constructor injection — dependency provided at creation time
        this.bookRepository = bookRepository;
        this.serviceName    = serviceName;
        System.out.println("[BookService] Constructor Injection: "
                + "BookRepository injected via constructor");
    }

    // ============================================================
    // Business Logic Methods
    // ============================================================

    public void displayAllBooks() {
        System.out.println("\n========================================");
        System.out.println("  " + serviceName + " — All Books");
        System.out.println("  Repository: "
                + bookRepository.getRepositoryType());
        System.out.println("========================================");

        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("  No books available.");
        } else {
            books.forEach(book ->
                System.out.printf("  [%d] %-30s | %-20s | %s%n",
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.isAvailable() ? "Available" : "Borrowed"
                )
            );
        }
        System.out.println("  Total: " + bookRepository.count() + " books");
        System.out.println("========================================");
    }

    public void addBook(int id, String title,
                        String author, String isbn) {
        System.out.println("\n--- Adding Book: " + title + " ---");
        Book newBook = new Book(id, title, author, isbn);
        bookRepository.save(newBook);
        System.out.println("Successfully added: " + title);
    }

    public void searchBookById(int id) {
        System.out.println("\n--- Searching by ID: " + id + " ---");
        Optional<Book> result = bookRepository.findById(id);
        result.ifPresentOrElse(
            book -> System.out.println("FOUND: " + book),
            ()   -> System.out.println("NOT FOUND: No book with ID " + id)
        );
    }

    public void searchBookByTitle(String title) {
        System.out.println("\n--- Searching by Title: " + title + " ---");
        Optional<Book> result = bookRepository.findByTitle(title);
        result.ifPresentOrElse(
            book -> System.out.println("FOUND: " + book),
            ()   -> System.out.println("NOT FOUND: '" + title
                        + "' not in library")
        );
    }

    public void borrowBook(int id) {
        System.out.println("\n--- Borrowing Book ID: " + id + " ---");
        Optional<Book> result = bookRepository.findById(id);
        result.ifPresentOrElse(
            book -> {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("SUCCESS: '"
                            + book.getTitle()
                            + "' has been borrowed.");
                } else {
                    System.out.println("UNAVAILABLE: '"
                            + book.getTitle()
                            + "' is already borrowed.");
                }
            },
            () -> System.out.println("NOT FOUND: Book ID " + id)
        );
    }

    public void returnBook(int id) {
        System.out.println("\n--- Returning Book ID: " + id + " ---");
        Optional<Book> result = bookRepository.findById(id);
        result.ifPresentOrElse(
            book -> {
                book.setAvailable(true);
                System.out.println("SUCCESS: '"
                        + book.getTitle()
                        + "' has been returned.");
            },
            () -> System.out.println("NOT FOUND: Book ID " + id)
        );
    }

    public void deleteBook(int id) {
        System.out.println("\n--- Deleting Book ID: " + id + " ---");
        boolean deleted = bookRepository.delete(id);
        System.out.println(deleted
            ? "SUCCESS: Book ID " + id + " deleted."
            : "FAILED: Book ID " + id + " not found.");
    }

    // Getters
    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public String getServiceName() {
        return serviceName;
    }
}
