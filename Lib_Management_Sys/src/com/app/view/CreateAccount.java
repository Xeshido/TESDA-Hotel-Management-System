
package com.app.view;


import com.app.controller.UserController;

import com.app.model.User;
import java.util.Scanner;


public class CreateAccount {
    public static void main(String[] args) {
        UserController u = new UserController();
        Scanner sc = new Scanner(System.in);
        
        User user = new User();
        System.out.print("Enter username: ");
        user.setName(sc.nextLine());
        System.out.print("Enter password: ");
        user.setPassword(sc.nextLine());
        System.out.print("Enter name: ");
        user.setFname(sc.nextLine());
        System.out.print("Enter level: ");
        user.setLevel(sc.nextLine());
        
        
        u.createAccount(user);
    }
}
