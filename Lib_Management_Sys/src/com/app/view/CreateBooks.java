
package com.app.view;

import com.app.controller.BookController;
import com.app.model.Book;
import java.util.Scanner;


public class CreateBooks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookController bcontrol = new BookController();
        
        Book book = new Book();
        System.out.print("Enter Book Title: ");
        book.setTitle(sc.nextLine());
        System.out.print("Enter Book Author: ");
        book.setAuthor(sc.nextLine());
        System.out.print("Enter Book Category: ");
        book.setCategoryName(sc.nextLine());
        
        bcontrol.createBooks(book);
        
        
    }
}
