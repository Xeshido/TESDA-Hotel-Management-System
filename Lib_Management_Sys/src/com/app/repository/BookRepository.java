
package com.app.repository;

import com.app.model.Book;


public interface BookRepository {
    public void displayBooks(Book book);
    public void createBooks(Book book);
    public void updateBooks(Book book);
}
