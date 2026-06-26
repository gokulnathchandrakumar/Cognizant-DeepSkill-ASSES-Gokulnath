package com.library.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * BookRepository — responsible for data access operations.
 * In a real application this would talk to a database.
 * Here we simulate with an in-memory list.
 *
 * Spring manages this as a Bean — it creates ONE instance
 * and reuses it wherever it's needed (Singleton by default).
 */
public class BookRepository {

    // Injected by Spring via setLibraryName()
    private String libraryName;

    // Simulated in-memory "database"
    private List<String> books = new ArrayList<>();

    // Called by Spring after creating the bean
    // (because we defined it in applicationContext.xml)
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
        // Initialize with some sample books
        books.add("The Great Gatsby");
        books.add("To Kill a Mockingbird");
        books.add("Clean Code");
        books.add("Effective Java");
        books.add("Spring in Action");
        System.out.println("[BookRepository] Bean created for: "
                + libraryName);
    }

    public String getLibraryName() {
        return libraryName;
    }

    public List<String> getAllBooks() {
        return books;
    }

    public void addBook(String bookTitle) {
        books.add(bookTitle);
        System.out.println("[BookRepository] Added book: " + bookTitle);
    }

    public boolean removeBook(String bookTitle) {
        boolean removed = books.remove(bookTitle);
        if (removed) {
            System.out.println("[BookRepository] Removed book: " + bookTitle);
        } else {
            System.out.println("[BookRepository] Book not found: " + bookTitle);
        }
        return removed;
    }

    public boolean findBook(String bookTitle) {
        return books.contains(bookTitle);
    }
}
