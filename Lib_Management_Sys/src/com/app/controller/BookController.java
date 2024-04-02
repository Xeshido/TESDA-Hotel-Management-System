
package com.app.controller;

import com.app.db.DbConnection;
import com.app.model.Book;
import com.app.repository.BookRepository;

//book_id	book_title	book_author	category_name	archived	

public class BookController extends DbConnection implements BookRepository{
    // RETRIEVE/READ API
    @Override
    public void displayBooks(Book book){
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(DISPLAY_BOOKS);
            System.out.println("ID\tTITLE\t\t\t\t AUTHOR\t\tCATEGORY");
            while(result.next()){
                book.setId(result.getInt("book_id"));
                book.setTitle(result.getString("book_title"));
                book.setAuthor(result.getString("book_author"));
                book.setCategoryName(result.getString("category_name"));
                System.out.println(book.getId() + "\t" + book.getTitle() + "\t\t" 
                    + book.getAuthor() + "\t" + book.getCategoryName());
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void createBooks(Book book){
        try {
            connect();
            prep = con.prepareStatement(CREATE_BOOKS);
            prep.setString(1, book.getTitle());
            prep.setString(2, book.getAuthor());
            prep.setString(3, book.getCategoryName());
            prep.executeUpdate();
            System.out.println("Book " + book.getTitle() + " added successfully!");
            displayBooks(book);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    @Override
    public void updateBooks(Book book){
        try {
            connect();
            prep = con.prepareStatement(UPDATE_BOOKS);
            prep.setString(1, book.getTitle());
            prep.setInt(2, book.getId());
            prep.executeUpdate();
            System.out.println("Book " + book.getId() + " updated successfully!");
            displayBooks(book);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
