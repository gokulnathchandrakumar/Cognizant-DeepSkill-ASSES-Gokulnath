package com.library.service;

import com.library.repository.BookRepository;
import java.util.List;

/**
 * BookService — contains business logic for library operations.
 * It DEPENDS on BookRepository for data access.
 *
 * Notice: BookService does NOT create BookRepository itself.
 * Spring INJECTS it via setBookRepository() — this is
 * Dependency Injection (DI), which we'll explore more in Exercise 2.
 */
public class BookService {

    // Dependency — Spring will inject this
    private BookRepository bookRepository;

    // Spring calls this setter to inject the BookRepository bean
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] Bean created. " +
                "BookRepository successfully injected.");
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    // ---- Business Logic Methods ----

    public void displayAllBooks() {
        System.out.println("\n--- All Books in "
                + bookRepository.getLibraryName() + " ---");
        List<String> books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
        System.out.println("Total books: " + books.size());
    }

    public void addBook(String title) {
        System.out.println("\n--- Adding Book ---");
        bookRepository.addBook(title);
        System.out.println("Book '" + title
                + "' added successfully to "
                + bookRepository.getLibraryName());
    }

    public void removeBook(String title) {
        System.out.println("\n--- Removing Book ---");
        boolean success = bookRepository.removeBook(title);
        if (success) {
            System.out.println("Book '" + title
                    + "' removed from "
                    + bookRepository.getLibraryName());
        } else {
            System.out.println("Book '" + title + "' was not found.");
        }
    }

    public void searchBook(String title) {
        System.out.println("\n--- Searching for: " + title + " ---");
        boolean found = bookRepository.findBook(title);
        if (found) {
            System.out.println("FOUND: '" + title
                    + "' is available in "
                    + bookRepository.getLibraryName());
        } else {
            System.out.println("NOT FOUND: '" + title
                    + "' is not available.");
        }
    }

    public int getTotalBooks() {
        return bookRepository.getAllBooks().size();
    }
}
