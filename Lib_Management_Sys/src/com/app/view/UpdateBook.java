
package com.app.view;

import com.app.controller.BookController;
import com.app.model.Book;
import java.util.Scanner;


public class UpdateBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookController bc = new BookController();
        
        Book book = new Book();
        System.out.print("Enter a new Title: ");
        book.setTitle(sc.nextLine());
        System.out.print("Enter ID of book that you want to update name: ");
        book.setId(sc.nextInt());
        
        bc.updateBooks(book);
    }
}
