
package com.app.model;

//book_id	book_title	book_author	category_name	archived	

public class Book {
    private int id;
    private String title;
    private String author;
    private String categoryName;
    
    public Book(){}

    public Book(int id, String title, String author, String categoryName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    
}
