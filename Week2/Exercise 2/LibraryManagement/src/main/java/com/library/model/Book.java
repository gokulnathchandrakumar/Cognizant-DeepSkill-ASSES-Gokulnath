package com.library.model;

/**
 * Book — simple model/entity class
 * Represents a book in the library system
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(int id, String title, String author, String isbn) {
        this.id        = id;
        this.title     = title;
        this.author    = author;
        this.isbn      = isbn;
        this.available = true; // available by default
    }

    // Getters and Setters
    public int getId()             { return id; }
    public String getTitle()       { return title; }
    public String getAuthor()      { return author; }
    public String getIsbn()        { return isbn; }
    public boolean isAvailable()   { return available; }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format(
            "Book{id=%d, title='%s', author='%s', isbn='%s', available=%s}",
            id, title, author, isbn, available
        );
    }
}
