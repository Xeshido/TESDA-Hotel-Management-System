
package com.app.view;


import com.app.controller.BookController;
import com.app.model.Book;
import java.util.Scanner;


public class DisplayBooks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookController bcontrol = new BookController();
        Book book = new Book();
        bcontrol.displayBooks(book);
    }
}
