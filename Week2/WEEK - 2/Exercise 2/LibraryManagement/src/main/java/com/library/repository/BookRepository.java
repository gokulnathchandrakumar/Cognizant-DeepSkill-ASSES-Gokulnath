package com.library.repository;

import com.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * BookRepository — data access layer
 * Simulates a database using an in-memory list
 *
 * Spring manages this as a Singleton Bean
 * One instance shared across the entire application
 */
public class BookRepository {

    private String repositoryType;
    private List<Book> books = new ArrayList<>();

    // ============================================================
    // SETTER INJECTION point
    // Spring calls this after creating the BookRepository bean
    // because we defined:
    // <property name="repositoryType" value="In-Memory Database"/>
    // in applicationContext.xml
    // ============================================================
    public void setRepositoryType(String repositoryType) {
        this.repositoryType = repositoryType;
        initializeSampleData();
        System.out.println("[BookRepository] Initialized with type: "
                + repositoryType);
    }

    public String getRepositoryType() {
        return repositoryType;
    }

    private void initializeSampleData() {
        books.add(new Book(1, "Clean Code",
                "Robert C. Martin", "978-0132350884"));
        books.add(new Book(2, "Effective Java",
                "Joshua Bloch", "978-0134685991"));
        books.add(new Book(3, "Spring in Action",
                "Craig Walls", "978-1617294945"));
        books.add(new Book(4, "Design Patterns",
                "GoF", "978-0201633610"));
        books.add(new Book(5, "The Pragmatic Programmer",
                "Andrew Hunt", "978-0135957059"));
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public Optional<Book> findById(int id) {
        return books.stream()
                    .filter(b -> b.getId() == id)
                    .findFirst();
    }

    public Optional<Book> findByTitle(String title) {
        return books.stream()
                    .filter(b -> b.getTitle()
                    .equalsIgnoreCase(title))
                    .findFirst();
    }

    public void save(Book book) {
        books.add(book);
        System.out.println("[BookRepository] Saved: " + book.getTitle());
    }

    public boolean delete(int id) {
        return books.removeIf(b -> b.getId() == id);
    }

    public int count() {
        return books.size();
    }
}
